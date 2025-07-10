CREATE TABLE users
(
    id               BIGSERIAL PRIMARY KEY,
    full_name        VARCHAR(255) NOT NULL,
    email            VARCHAR(255) NOT NULL UNIQUE,
    telegram_contact VARCHAR(255),
    role             VARCHAR(20) NOT NULL
);