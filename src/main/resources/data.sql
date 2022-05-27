INSERT INTO categories (name)
VALUES ('TVs'), ('Tablets'), ('Laptops'), ('Phones'), ('Headphones');

INSERT INTO products (title, price, category_id)
VALUES ('Samsung', 200, 1), ('LG', 180, 1), ('Sharp', 250, 1),
       ('Apple', 600, 2), ('Samsung', 500, 2), ('Lenovo', 200, 2),
       ('Lenovo', 600, 3), ('Macbook', 1500, 3), ('Dell', 1000, 3),
       ('Xiaomi', 200, 4), ('Huawei', 300, 4), ('Iphone', 800, 4),
       ('JBL', 200, 5), ('Beats', 400, 5), ('Sony', 100, 5);
