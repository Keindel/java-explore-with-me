DROP TABLE IF EXISTS endpoint_hit;

CREATE TABLE IF NOT EXISTS endpoint_hit
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    app       VARCHAR(64)                                     NOT NULL,
    uri       VARCHAR(256)                                    NOT NULL,
    ip        VARCHAR(64),
    timestamp TIMESTAMP                                       NOT NULL
);