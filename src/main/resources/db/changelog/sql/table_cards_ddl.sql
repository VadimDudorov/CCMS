CREATE TABLE IF NOT EXISTS client.cards (
    id              BIGSERIAL PRIMARY KEY,
    user_id         INTEGER NOT NULL,
    'number'        VARCHAR NOT NULL,
    owner           VARCHAR NOT NULL,
    expiration_date DATE NOT NULL,
    status          VARCHAR NOT NULL,
    balance         NUMERIC NOT NULL,
    limit           NUMERIC NULL,
    name_limit      VARCHAR NULL
);