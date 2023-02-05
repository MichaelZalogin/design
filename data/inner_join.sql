CREATE TABLE company
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(128),
    date DATE
);

INSERT INTO company(id, name, date)
VALUES (1, 'Google', '2001-01-01'),
       (2, 'Apple', '2002-10-29'),
       (3, 'Facebook', '1995-09-13'),
       (4, 'Amazon', '1993-01-11'),
       (5, 'Netflix', '1986-12-21');

CREATE TABLE employee
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(128),
    last_name  VARCHAR(128),
    salary     INT,
    company_id INT REFERENCES company (id)
);

INSERT INTO employee(first_name, last_name, salary, company_id)
VALUES ('Ivan', 'Sidorov', 500, 1),
       ('Ivan', 'Ivanov', 1000, 2),
       ('Arni', 'Paramonov', 2000, 2),
       ('Petr', 'Petrov', 2000, 3),
       ('Sveta', 'Svetikova', 1500, 1),
       ('Andry', 'Sidorov', 2500, 4);

SELECT first_name, last_name, salary, company_id, name, date
FROM employee e
         INNER JOIN company c ON c.id = e.company_id;

SELECT name company_name, COUNT(first_name) employee_count
FROM company c
         INNER JOIN employee e ON c.id = e.company_id
GROUP BY name
HAVING COUNT(first_name) > 1;

SELECT name, first_name, last_name
FROM company c
         INNER JOIN employee e ON c.id = e.company_id;