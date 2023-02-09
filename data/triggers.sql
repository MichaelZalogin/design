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

CREATE OR REPLACE FUNCTION tax()
    RETURNS trigger AS
$$
BEGIN
    UPDATE products
    SET price = price + price * 0.2
    WHERE id = (SELECT id FROM inserted);
    RETURN new;
END;
$$
    LANGUAGE 'plpgsql';

--После вставки данных statement уровень
CREATE TRIGGER tax_trigger
    AFTER INSERT
    ON products
    REFERENCING new TABLE AS inserted
    FOR EACH STATEMENT
EXECUTE PROCEDURE tax();

--До вставки данных row уровень
CREATE TRIGGER tax_trigger2
    BEFORE INSERT
    ON products
    FOR EACH ROW
EXECUTE PROCEDURE tax();

CREATE OR REPLACE FUNCTION delivery()
    RETURNS trigger AS
$$
BEGIN
    INSERT INTO history_of_price (name, price, date)
    VALUES (new.name, new.price, NOW());
    RETURN new;
END;
$$
    LANGUAGE 'plpgsql';

