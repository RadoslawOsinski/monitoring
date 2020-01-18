CREATE TABLE IF NOT EXISTS history (
    id numeric(10,0) NOT NULL,
    code VARCHAR(50) NOT NULL,
    description VARCHAR(500),
    CONSTRAINT history_pk PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS history_id_seq
  start 1
  increment 1;
