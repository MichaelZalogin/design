CREATE TABLE type
(
    id   serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE product
(
    id           serial PRIMARY KEY,
    name         varchar(255),
    type_id      INT REFERENCES type (id),
    expired_date date,
    price        float
);

INSERT INTO type (name)
VALUES ('Сыр');
INSERT INTO type (name)
VALUES ('Мороженое');
INSERT INTO type (name)
VALUES ('Молоко');

INSERT INTO product (name, type_id, expired_date, price)
VALUES ('Шоколадное мороженое', 2, '2023-11-23', 54.34);
INSERT INTO product (name, type_id, expired_date, price)
VALUES ('Мороженое с шоколадной крошкой', 2, '2023-07-13', 49.15);
INSERT INTO product (name, type_id, expired_date, price)
VALUES ('Ванильное мороженое с клубникой', 2, '2023-03-29', 79.15);
INSERT INTO product (name, type_id, expired_date, price)
VALUES ('Сыр плавленный', 1, '2023-01-23', 154.44);
INSERT INTO product (name, type_id, expired_date, price)
VALUES ('Сыр моцарелла', 1, '2023-08-14', 234.70);
INSERT INTO product (name, type_id, expired_date, price)
VALUES ('Сыр "Косичка"', 1, '2023-05-26', 79.80);
INSERT INTO product (name, type_id, expired_date, price)
VALUES ('Молоко 2,5% жирности', 3, '2023-03-12', 50.80);
INSERT INTO product (name, type_id, expired_date, price)
VALUES ('Молоко 3,5% жирности', 3, '2023-01-12', 73.80);
INSERT INTO product (name, type_id, expired_date, price)
VALUES ('Молоко 5% жирности', 3, '2023-07-19', 112.80);

SELECT p.name, expired_date, price, t.name
FROM product p
         INNER JOIN public.type t ON t.id = p.type_id
WHERE t.name ILIKE 'СЫР';

SELECT name, price
FROM product
WHERE name ILIKE '%МОРОЖЕНОЕ%';

SELECT name, expired_date
FROM product
WHERE expired_date < NOW();

SELECT p.name, expired_date, price
FROM product p
WHERE price = (SELECT MAX(price) FROM product);

SELECT t.name, COUNT(*)
FROM type t
         INNER JOIN product p ON t.id = p.type_id
GROUP BY t.name;

SELECT p.name, t.name
FROM product p
         INNER JOIN public.type t ON t.id = p.type_id
WHERE t.name ILIKE 'СЫР'
   OR t.name ILIKE 'МОЛОКО';

SELECT t.name, COUNT(*)
FROM type t
         INNER JOIN product p ON t.id = p.type_id
GROUP BY t.name
HAVING COUNT(*) < 10;

SELECT p.name, expired_date, price, t.name
FROM product p
         INNER JOIN public.type t ON t.id = p.type_id;