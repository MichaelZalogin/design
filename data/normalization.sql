CREATE TABLE genders
(
    id     serial PRIMARY KEY,
    gender varchar(55)
);

INSERT INTO genders (gender)
VALUES ('male');
INSERT INTO genders (gender)
VALUES ('female');

CREATE TABLE films
(
    id   serial PRIMARY KEY,
    name text
);

INSERT INTO films (name)
VALUES ('Pirates of the Caribbean');
INSERT INTO films (name)
VALUES ('Matrix: Revolution');
INSERT INTO films (name)
VALUES ('The man who changed everything');
INSERT INTO films (name)
VALUES ('Interstellar');

CREATE TABLE rental
(
    id        serial PRIMARY KEY,
    name      varchar(255),
    surname   varchar(255),
    adress    text,
    id_gender int REFERENCES genders (id),
    UNIQUE (name, surname, adress)
);

INSERT INTO rental (name, surname, adress, id_gender)
VALUES ('Olga', 'Egorova', '1-ый Казанский переулок, д.14', 2);
INSERT INTO rental (name, surname, adress, id_gender)
VALUES ('Sergey', 'Ivanov', 'ул.Центральная, д.40, кв.74', 1);
INSERT INTO rental (name, surname, adress, id_gender)
VALUES ('Sergey', 'Ivanov', 'ул. Ленина, д.7, кв.130', 1);

CREATE TABLE rental_films
(
    id_rental int REFERENCES rental (id),
    id_film   int REFERENCES films (id),
    CONSTRAINT rental_films_pkey PRIMARY KEY (id_rental, id_film)
);

INSERT INTO rental_films (id_rental, id_film)
VALUES (1, 1);
INSERT INTO rental_films (id_rental, id_film)
VALUES (1, 2);
INSERT INTO rental_films (id_rental, id_film)
VALUES (2, 3);
INSERT INTO rental_films (id_rental, id_film)
VALUES (2, 4);
INSERT INTO rental_films (id_rental, id_film)
VALUES (3, 2);