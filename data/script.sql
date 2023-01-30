
CREATE TABLE employees (
id serial PRIMARY KEY,
surname varchar(255),
department text,
manager boolean
);

SELECT * FROM employees;

INSERT INTO employees (surname,department,manager)
VALUES ('Ivanov', 'Google', true);

UPDATE employees SET department = 'Apple';

DELETE FROM employees;