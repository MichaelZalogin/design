CREATE TABLE car_bodies
(
    id   serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE car_engines
(
    id   serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE car_transmissions
(
    id   serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE cars
(
    id              serial PRIMARY KEY,
    name            varchar(255),
    body_id         int REFERENCES car_bodies (id),
    engine_id       int REFERENCES car_engines (id),
    transmission_id int REFERENCES car_transmissions (id)
);

INSERT INTO car_bodies (name)
VALUES ('Пикап');
INSERT INTO car_bodies (name)
VALUES ('Седан');
INSERT INTO car_bodies (name)
VALUES ('Купе');
INSERT INTO car_bodies (name)
VALUES ('Универсал');
INSERT INTO car_bodies (name)
VALUES ('Хетчбек');

INSERT INTO car_engines (name)
VALUES ('V4');
INSERT INTO car_engines (name)
VALUES ('V6');
INSERT INTO car_engines (name)
VALUES ('V8');
INSERT INTO car_engines (name)
VALUES ('V12');
INSERT INTO car_engines (name)
VALUES ('V14');

INSERT INTO car_transmissions (name)
VALUES ('GL-5');
INSERT INTO car_transmissions (name)
VALUES ('GL-4');
INSERT INTO car_transmissions (name)
VALUES ('GL-3');
INSERT INTO car_transmissions (name)
VALUES ('GL-2');
INSERT INTO car_transmissions (name)
VALUES ('GL-1');

INSERT INTO cars (name, body_id, engine_id, transmission_id)
VALUES ('Nissan', 1, 1, 1);
INSERT INTO cars (name, body_id, engine_id, transmission_id)
VALUES ('Toyota', 2, 2, 2);
INSERT INTO cars (name, body_id, engine_id, transmission_id)
VALUES ('Ford', 3, 3, 2);
INSERT INTO cars (name, body_id, engine_id, transmission_id)
VALUES ('BMV', 5, 4, 4);
INSERT INTO cars (name, body_id, engine_id, transmission_id)
VALUES ('Mercedes', 5, 4, 5);
INSERT INTO cars (name, body_id, engine_id, transmission_id)
VALUES ('Opel', 3, 1, 1);

SELECT c.id, c.name, b.name, e.name, t.name
FROM cars c
         LEFT JOIN car_bodies b ON b.id = c.body_id
         LEFT JOIN car_engines e ON e.id = c.engine_id
         LEFT JOIN car_transmissions t ON t.id = c.transmission_id;

SELECT b.name
FROM car_bodies b
         LEFT JOIN cars c ON b.id = c.body_id
WHERE c.name IS NULL;

SELECT e.name
FROM car_engines e
         LEFT JOIN cars c ON e.id = c.engine_id
WHERE c.name IS NULL;

SELECT t.name
FROM car_transmissions t
         LEFT JOIN cars c ON t.id = c.transmission_id
WHERE c.name IS NULL;

CREATE VIEW show_all_cars_with_parts AS
SELECT c.id   AS car_id,
       c.name AS car_name,
       b.name AS body_name,
       e.name AS engine_name,
       t.name AS transmission_name
FROM cars c
         LEFT JOIN car_bodies b ON b.id = c.body_id
         LEFT JOIN car_engines e ON e.id = c.engine_id
         LEFT JOIN car_transmissions t ON t.id = c.transmission_id;

SELECT *
FROM show_all_cars_with_parts;
