
CREATE TABLE employees (
id serial PRIMARY KEY,
name varchar (255),
surname varchar(255),
department text
);

SELECT * FROM employees;

INSERT INTO employees (name,surname,department)
VALUES ('Ivan', 'Ivanov', 'Google');

UPDATE employees SET department = 'Apple';

DELETE FROM employees;