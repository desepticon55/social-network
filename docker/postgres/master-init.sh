#!/bin/bash
set -e

# Ждем готовности PostgreSQL
until pg_isready -U "$POSTGRES_USER"; do
  sleep 1
done

# Настройка pg_hba.conf
cat >> /var/lib/postgresql/data/pg_hba.conf << EOF
# Replication rules
host  replication  replicator  0.0.0.0/0  md5
EOF

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER ntwrk WITH password 'ntwrk';
    CREATE USER ntwrk_ms WITH password 'ntwrk_ms';

    CREATE SCHEMA AUTHORIZATION ntwrk;

    REVOKE CREATE ON SCHEMA public FROM PUBLIC;

    GRANT USAGE ON SCHEMA ntwrk TO ntwrk_ms;

    ALTER DEFAULT PRIVILEGES FOR USER ntwrk IN SCHEMA ntwrk GRANT
    SELECT,
    INSERT,
    UPDATE,
    DELETE,
    TRUNCATE
    ON TABLES TO ntwrk_ms;

    ALTER DEFAULT PRIVILEGES FOR USER ntwrk IN SCHEMA ntwrk GRANT USAGE ON SEQUENCES TO ntwrk_ms;

    ALTER DEFAULT PRIVILEGES FOR USER ntwrk GRANT USAGE ON SCHEMAS TO ntwrk;

    ALTER DEFAULT PRIVILEGES FOR USER ntwrk IN SCHEMA ntwrk GRANT EXECUTE ON FUNCTIONS TO ntwrk_ms;

    DO \$$
    BEGIN
      IF NOT EXISTS (SELECT FROM pg_catalog.pg_user WHERE usename = 'replicator') THEN
        CREATE USER replicator WITH REPLICATION ENCRYPTED PASSWORD '${POSTGRES_REPLICATION_PASSWORD}';
      END IF;
    END
    \$$;

    SELECT pg_drop_replication_slot(slot_name)
    FROM pg_replication_slots
    WHERE slot_name IN ('replication_slot_slave1', 'replication_slot_slave2');

    ALTER SYSTEM SET wal_level = replica;
    ALTER SYSTEM SET max_wal_senders = 10;
    ALTER SYSTEM SET max_replication_slots = 10;
    ALTER SYSTEM SET hot_standby = on;

    SELECT pg_create_physical_replication_slot('replication_slot_slave1');
    SELECT pg_create_physical_replication_slot('replication_slot_slave2');

    SELECT pg_reload_conf();
EOSQL