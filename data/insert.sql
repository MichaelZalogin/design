INSERT INTO users (user_name, user_surname, role)
VALUES ('Ivan', 'Ivanov', 1),
       ('Petr', 'Petrov', 2),
       ('Andy', 'Smith', 1);

INSERT INTO role (role_name)
VALUES ('Manager'),
       ('Employee'),
       ('Director');

INSERT INTO rules (remove_item, edit_item, edit_status)
VALUES (TRUE, TRUE, TRUE),
       (TRUE, TRUE, FALSE),
       (TRUE, FALSE, TRUE),
       (TRUE, FALSE, FALSE),
       (FALSE, TRUE, TRUE),
       (FALSE, TRUE, FALSE),
       (FALSE, FALSE, TRUE),
       (FALSE, FALSE, FALSE);

INSERT INTO role_rules_compose (role, rule)
VALUES (3, 1),
       (2, 5),
       (1, 8);

INSERT INTO item (item_name, create_date, user_id, category, state)
VALUES ('order 1', '2022-02-02', 2, 1, 1),
       ('order 2', '2022-02-02', 2, 1, 2),
       ('order 3', '2022-02-02', 3, 2, 1);

INSERT INTO comments (comment, comment_date, item)
VALUES ('Anything text number 1', '2022-02-02', 1),
       ('Anything text number 2', '2022-02-02', 2),
       ('Anything text number 3', '2022-02-02', 1);

INSERT INTO attachs (format_file, item)
VALUES ('file format 1', 2),
       ('file format 2', 1),
       ('file format 3', 2),
       ('file format 4', 3),
       ('file format 5', 3);

INSERT INTO category (category_name)
VALUES ('fast'),
       ('slow'),
       ('medium');

INSERT INTO state (status_state, state_item)
VALUES (TRUE, 'Anything state 1'),
       (FALSE, 'Anything state 2'),
       (TRUE, 'Anything state 3');

