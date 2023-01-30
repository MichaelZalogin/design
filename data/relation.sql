-- one-to-many
CREATE TABLE mother (
id serial PRIMARY KEY,
name varchar(50),
last_name varchar(255)
);

CREATE TABLE child (
id serial PRIMARY KEY,
name varchar(50),
last_name varchar(255),
mother_id int REFERENCES mother(id)
);

--one-to-one
CREATE TABLE official_car (
id serial PRIMARY KEY,
number char(8)
);

CREATE TABLE employee (
id serial PRIMARY KEY,
name varchar(50),
last_name varchar(255),
official_car_id int REFERENCES car(id)
);

--many-to-many
CREATE TABLE book (
id serial PRIMARY KEY,
title varchar(50)
);

CREATE TABLE author (
id serial PRIMARY KEY,
name varchar(50),
last_name varchar(255)
);

CREATE TABLE book_author (
id_book int REFERENCES book(id),
id_author int REFERENCES author(id),
CONSTRAINT book_author_pkey PRIMARY KEY (id_book, id_author)
);