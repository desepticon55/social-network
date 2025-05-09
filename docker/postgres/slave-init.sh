#!/bin/bash
set -e

# Ждем готовности мастера
until pg_isready -h db-master -U replicator; do
  sleep 1
  echo "Ожидание готовности мастера..."
done

# Определяем имя слота в зависимости от имени контейнера
SLOT_NAME="replication_slot_slave${REPLICA_NUMBER}"

echo "Инициализация реплики с использованием слота: $SLOT_NAME"
rm -rf /var/lib/postgresql/data/*

PGPASSWORD="replicationpass" pg_basebackup \
  -h db-master \
  -U replicator \
  -p 5432 \
  -D /var/lib/postgresql/data \
  -Fp \
  -Xs \
  -P \
  -R \
  -S "$SLOT_NAME"

echo "Настройка подключения к мастеру..."
cat > /var/lib/postgresql/data/postgresql.auto.conf << EOF
primary_conninfo = 'host=db-master port=5432 user=replicator password=replicationpass'
primary_slot_name = '$SLOT_NAME'
EOF

touch /var/lib/postgresql/data/standby.signal