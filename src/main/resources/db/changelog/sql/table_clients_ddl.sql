CREATE SCHEMA IF NOT EXISTS client;

CREATE TABLE IF NOT EXISTS client.clients (
    user_id    BIGSERIAL PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    last_name  VARCHAR NOT NULL,
    email      VARCHAR NOT NULL,
    birth_date DATE NOT NULL
);