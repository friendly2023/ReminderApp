CREATE TABLE users
(
    id               BIGSERIAL PRIMARY KEY,
    username         VARCHAR(255) NOT NULL UNIQUE,
    email            VARCHAR(255) NOT NULL UNIQUE,
    telegram_contact VARCHAR(255),
    password         VARCHAR(255) NOT NULL
);