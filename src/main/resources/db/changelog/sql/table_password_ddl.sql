CREATE TABLE IF NOT EXISTS client.password (
    id       BIGSERIAL PRIMARY KEY,
    login    VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    roles    VARCHAR NOT NULL
);