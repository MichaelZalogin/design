CREATE TABLE devices
(
    id    serial PRIMARY KEY,
    name  varchar(255),
    price float
);

CREATE TABLE people
(
    id   serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE devices_people
(
    id        serial PRIMARY KEY,
    device_id int REFERENCES devices (id),
    people_id int REFERENCES people (id)
);

INSERT INTO devices (name, price)
VALUES ('Apple', 2003.32);
INSERT INTO devices (name, price)
VALUES ('Xiaomi', 1000.50);
INSERT INTO devices (name, price)
VALUES ('Poco', 1300.12);
INSERT INTO devices (name, price)
VALUES ('Samsung', 1854.73);
INSERT INTO devices (name, price)
VALUES ('Siemens', 120.41);

INSERT INTO people (name)
VALUES ('Ivan');
INSERT INTO people (name)
VALUES ('Petr');
INSERT INTO people (name)
VALUES ('Sveta');
INSERT INTO people (name)
VALUES ('Arni');
INSERT INTO people (name)
VALUES ('Andry');

INSERT INTO devices_people (device_id, people_id)
VALUES (1, 4);
INSERT INTO devices_people (device_id, people_id)
VALUES (2, 2);
INSERT INTO devices_people (device_id, people_id)
VALUES (3, 5);
INSERT INTO devices_people (device_id, people_id)
VALUES (3, 4);
INSERT INTO devices_people (device_id, people_id)
VALUES (4, 2);
INSERT INTO devices_people (device_id, people_id)
VALUES (5, 2);
INSERT INTO devices_people (device_id, people_id)
VALUES (2, 1);
INSERT INTO devices_people (device_id, people_id)
VALUES (2, 1);

SELECT AVG(price)
FROM devices;

SELECT p.name, AVG(price)
FROM people p
         INNER JOIN devices d ON p.id = d.id
GROUP BY p.name
HAVING AVG(price) > 2000