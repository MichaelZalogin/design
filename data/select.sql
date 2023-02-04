CREATE TABLE fauna
(
    id             serial PRIMARY KEY,
    name           text,
    avg_age        int,
    discovery_date date
);

INSERT INTO fauna (name, avg_age, discovery_date)
VALUES ('lion', 15000, '2022-01-15');
INSERT INTO fauna (name, avg_age, discovery_date)
VALUES ('wolf', 10000, '1966-12-19');
INSERT INTO fauna (name, avg_age, discovery_date)
VALUES ('bear', 20000, NULL);
INSERT INTO fauna (name, avg_age, discovery_date)
VALUES ('rhino', 30000, '1968-11-05');
INSERT INTO fauna (name, avg_age, discovery_date)
VALUES ('giraffe', 25000, '1875-09-29');
INSERT INTO fauna (name, avg_age, discovery_date)
VALUES ('snake', 5000, '1864-02-28');
INSERT INTO fauna (name, avg_age, discovery_date)
VALUES ('camel', 40000, '1743-04-30');
INSERT INTO fauna (name, avg_age, discovery_date)
VALUES ('monkey', 2500, '1852-06-13');
INSERT INTO fauna (name, avg_age, discovery_date)
VALUES ('hippo', 5000, '1843-11-11');
INSERT INTO fauna (name, avg_age, discovery_date)
VALUES ('fish', 300, '1933-05-07');

SELECT id, name, avg_age, discovery_date
FROM fauna
WHERE name = 'fish';

SELECT id, name, avg_age, discovery_date
FROM fauna
WHERE avg_age BETWEEN 10000 AND 21000;

SELECT id, name, avg_age, discovery_date
FROM fauna
WHERE discovery_date IS NULL;

SELECT id, name, avg_age, discovery_date
FROM fauna
WHERE discovery_date < '01-01-1950'