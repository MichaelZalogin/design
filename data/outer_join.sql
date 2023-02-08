CREATE TABLE departments
(
    id   serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE employees
(
    id             serial PRIMARY KEY,
    name           varchar(255),
    departments_id int REFERENCES departments (id)
);

CREATE TABLE teens
(
    id               serial PRIMARY KEY,
    id_employee_name int REFERENCES employees (id),
    gender           char(1) CHECK (gender LIKE 'm' OR gender LIKE 'f')
);

INSERT INTO departments (name)
VALUES ('IT');
INSERT INTO departments (name)
VALUES ('Student');
INSERT INTO departments (name)
VALUES ('Accountant');
INSERT INTO departments (name)
VALUES ('Intern');
INSERT INTO departments (name)
VALUES ('Doctor');


INSERT INTO employees (name, departments_id)
VALUES ('Ivan', 1);
INSERT INTO employees (name, departments_id)
VALUES ('Petr', 1);
INSERT INTO employees (name, departments_id)
VALUES ('Victor', 1);
INSERT INTO employees (name, departments_id)
VALUES ('Sveta', 2);
INSERT INTO employees (name, departments_id)
VALUES ('Arni', 2);
INSERT INTO employees (name, departments_id)
VALUES ('Andry', 3);
INSERT INTO employees (name, departments_id)
VALUES ('Artem', 3);
INSERT INTO employees (name, departments_id)
VALUES ('Igor', 4);
INSERT INTO employees (name, departments_id)
VALUES ('Anastasia', NULL);


SELECT e.name, d.name
FROM employees e
         LEFT JOIN departments d ON e.departments_id = d.id;

SELECT e.name, d.name
FROM employees e
         RIGHT JOIN departments d ON e.departments_id = d.id;

SELECT e.name, d.name
FROM employees e
         FULL JOIN departments d ON e.departments_id = d.id;

SELECT e.name, d.name
FROM employees e
         CROSS JOIN departments d;

SELECT d.name, e.name
FROM departments d
         LEFT JOIN employees e ON d.id = e.departments_id
WHERE e.name IS NULL;

SELECT e.name, d.name
FROM employees e
         LEFT JOIN departments d ON e.departments_id = d.id;
SELECT e.name, d.name
FROM departments d
         RIGHT JOIN employees e ON d.id = e.departments_id;

INSERT INTO teens (id_employee_name, gender)
VALUES (1, 'm');
INSERT INTO teens (id_employee_name, gender)
VALUES (2, 'm');
INSERT INTO teens (id_employee_name, gender)
VALUES (3, 'm');
INSERT INTO teens (id_employee_name, gender)
VALUES (4, 'f');
INSERT INTO teens (id_employee_name, gender)
VALUES (5, 'm');
INSERT INTO teens (id_employee_name, gender)
VALUES (6, 'm');
INSERT INTO teens (id_employee_name, gender)
VALUES (7, 'm');
INSERT INTO teens (id_employee_name, gender)
VALUES (8, 'm');
INSERT INTO teens (id_employee_name, gender)
VALUES (9, 'f');

SELECT e.name, e.gender, t.name, t.gender
FROM (SELECT e.name, t1.gender
      FROM teens t1
               JOIN employees e ON t1.id_employee_name = e.id
      WHERE gender = 'm') e
         CROSS JOIN (SELECT e.name, t1.gender
                     FROM teens t1
                              JOIN employees e ON t1.id_employee_name = e.id
                     WHERE gender = 'f') t;