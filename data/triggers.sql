CREATE TABLE products
(
    id       serial PRIMARY KEY,
    name     varchar(50),
    producer varchar(50),
    count    integer DEFAULT 0,
    price    integer
);

CREATE OR REPLACE FUNCTION discount()
    RETURNS trigger AS
$$
BEGIN
    UPDATE products
    SET price = price - price * 0.2
    WHERE count <= 5
      AND id = new.id;
    RETURN NEW;
END;
$$
    LANGUAGE 'plpgsql';

CREATE TRIGGER discount_trigger
    AFTER INSERT
    ON products
    FOR EACH ROW
EXECUTE PROCEDURE discount();

INSERT INTO products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

INSERT INTO products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

ALTER TABLE products
    DISABLE TRIGGER discount_trigger;

INSERT INTO products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

DROP TRIGGER discount_trigger ON products;

CREATE OR REPLACE FUNCTION tax()
    RETURNS trigger AS
$$
BEGIN
    UPDATE products
    SET price = price - price * 0.2
    WHERE id = (SELECT id FROM inserted)
      AND count <= 5;
    RETURN new;
END;
$$
    LANGUAGE 'plpgsql';

CREATE TRIGGER tax_trigger
    AFTER INSERT
    ON products
    REFERENCING new TABLE AS inserted
    FOR EACH STATEMENT
EXECUTE PROCEDURE tax();

DROP TRIGGER tax_trigger ON products;

-------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION tax_after_insert()
    RETURNS trigger AS
$$
BEGIN
    UPDATE products
    SET price = price + price * 0.2
    WHERE id = (SELECT id FROM inserted);
    RETURN NULL;
END;
$$
    LANGUAGE 'plpgsql';

CREATE TRIGGER tax_trigger_after_insert
    AFTER INSERT
    ON products
    REFERENCING new TABLE AS inserted
    FOR EACH STATEMENT
EXECUTE PROCEDURE tax_after_insert();

-------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION tax_before_insert()
    RETURNS trigger AS
$$
BEGIN
    new.price = new.price + new.price * 0.2;
    RETURN new;
END;
$$
    LANGUAGE 'plpgsql';

CREATE TRIGGER tax_trigger_before_insert
    BEFORE INSERT
    ON products
    FOR EACH ROW
EXECUTE PROCEDURE tax_before_insert();

-------------------------------------------------------------------------------------------

CREATE TABLE history_of_price
(
    id    serial PRIMARY KEY,
    name  varchar(50),
    price integer,
    date  timestamp
);

CREATE OR REPLACE FUNCTION delivery()
    RETURNS trigger AS
$$
BEGIN
    INSERT INTO history_of_price (name, price, date)
    VALUES (new.name, new.price, NOW());
    RETURN NULL;
END;
$$
    LANGUAGE 'plpgsql';

CREATE TRIGGER delivery_trigger2
    AFTER INSERT
    ON products
    FOR EACH ROW
EXECUTE PROCEDURE delivery();

CREATE OR REPLACE PROCEDURE insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    INSERT INTO products (name, producer, count, price)
    VALUES (i_name, prod, i_count, i_price);
END
$$;

CALL insert_data('product_2', 'producer_2', 15, 32);

CREATE OR REPLACE PROCEDURE update_data(u_count integer, tax float, u_id integer)
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    IF u_count > 0 THEN
        UPDATE products SET count = count - u_count WHERE id = u_id;
    END IF;
    IF tax > 0 THEN
        UPDATE products SET price = price + price * tax;
    END IF;
END;
$$;

CALL update_data(10, 0, 1);
CALL insert_data('product_1', 'producer_1', 3, 50);
CALL insert_data('product_3', 'producer_3', 8, 115);
CALL update_data(0, 0.2, 0);

DELETE
FROM products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

CREATE OR REPLACE FUNCTION f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
    RETURNS void
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    INSERT INTO products (name, producer, count, price)
    VALUES (i_name, prod, i_count, i_price);
END;
$$;

SELECT f_insert_data('product_1', 'producer_1', 25, 50);

CREATE OR REPLACE FUNCTION f_update_data(u_count integer, tax float, u_id integer)
    RETURNS integer
    LANGUAGE 'plpgsql'
AS
$$
DECLARE
    result integer;
BEGIN
    IF u_count > 0 THEN
        UPDATE products SET count = count - u_count WHERE id = u_id;
        SELECT INTO result count FROM products WHERE id = u_id;
    END IF;
    IF tax > 0 THEN
        UPDATE products SET price = price + price * tax;
        SELECT INTO result SUM(price) FROM products;
    END IF;
    RETURN result;
END;
$$;

SELECT f_update_data(10, 0, 1);

SELECT f_insert_data('product_2', 'producer_2', 15, 32);
SELECT f_insert_data('product_3', 'producer_3', 8, 115);

SELECT f_update_data(0, 0.2, 0);

CREATE OR REPLACE PROCEDURE delete_data(i_name varchar)
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    DELETE
    FROM products
    WHERE i_name LIKE products.name;
END
$$;

CREATE OR REPLACE FUNCTION f_delete_data(i_name varchar)
    RETURNS void
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    DELETE
    FROM products
    WHERE i_name LIKE products.name;
END;
$$;