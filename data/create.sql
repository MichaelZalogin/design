CREATE TABLE role
(
    role_id   serial PRIMARY KEY,
    role_name varchar(55)
);

CREATE TABLE rules
(
    rule_id     serial PRIMARY KEY,
    remove_item boolean,
    edit_item   boolean,
    edit_status boolean
);

CREATE TABLE role_rules_compose
(
    role int REFERENCES role (role_id),
    rule int REFERENCES rules (rule_id),
    CONSTRAINT role_rule_pkey PRIMARY KEY (role, rule)
);

CREATE TABLE users
(
    user_id      serial PRIMARY KEY,
    user_name    varchar(255),
    user_surname varchar(255),
    role         INT REFERENCES role (role_id)
);

CREATE TABLE category
(
    category_id   serial PRIMARY KEY,
    category_name varchar(255)
);

CREATE TABLE state
(
    state_id     serial PRIMARY KEY,
    status_state boolean,
    state_item   text
);

CREATE TABLE item
(
    item_id     serial PRIMARY KEY,
    item_name   varchar(55),
    create_date date,
    user_id     INT REFERENCES users (user_id),
    category    INT REFERENCES category (category_id),
    state       INT REFERENCES state (state_id)
);

CREATE TABLE comments
(
    comment_id   serial PRIMARY KEY,
    comment      text,
    comment_date date,
    item         INT REFERENCES item (item_id)
);

CREATE TABLE attachs
(
    attachs_id  serial PRIMARY KEY,
    format_file varchar(255),
    item        INT REFERENCES item (item_id)
);