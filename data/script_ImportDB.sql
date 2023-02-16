CREATE TABLE IF NOT EXISTS users
(
    id   serial PRIMARY KEY,
    name text,
    mail text UNIQUE
);