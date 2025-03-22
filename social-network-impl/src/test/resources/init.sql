CREATE USER ntwrk WITH password 'ntwrk';
CREATE USER ntwrk_ms WITH password 'ntwrk_ms';

CREATE SCHEMA AUTHORIZATION ntwrk;

REVOKE CREATE ON SCHEMA public FROM PUBLIC;

GRANT USAGE ON SCHEMA ntwrk TO ntwrk_ms;

ALTER
DEFAULT PRIVILEGES FOR USER ntwrk IN SCHEMA ntwrk GRANT
SELECT,
INSERT,
UPDATE,
DELETE,
TRUNCATE
ON TABLES TO ntwrk_ms;
ALTER
DEFAULT PRIVILEGES FOR USER ntwrk IN SCHEMA ntwrk GRANT USAGE ON SEQUENCES TO ntwrk_ms;
ALTER
DEFAULT PRIVILEGES FOR USER ntwrk GRANT USAGE ON SCHEMAS TO ntwrk;
ALTER
DEFAULT PRIVILEGES FOR USER ntwrk IN SCHEMA ntwrk GRANT EXECUTE ON FUNCTIONS TO ntwrk_ms;