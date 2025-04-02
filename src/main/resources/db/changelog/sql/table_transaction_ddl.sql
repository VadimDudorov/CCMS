CREATE TABLE IF NOT EXISTS client.transaction (
    id        BIGSERIAL PRIMARY KEY,
    card_id   INTEGER NOT NULL,
    operation VARCHAR NOT NULL,
    amount    NUMERIC NOT NULL
);