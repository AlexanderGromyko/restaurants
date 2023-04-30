INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANTS (NAME, description)
VALUES ('IKEA cafe', 'Cafe for IKEA customers'),
       ('Cafe', 'Small cafe'),
       ('British pub', 'Famous British pub');

INSERT INTO DISHES (NAME, restaurant_id, date_time, description, price)
VALUES ('Fried eggs', 1, '2023-04-30 10:00:00.00', '3 fried eggs', 4000),
       ('Bread', 1, '2023-04-30 10:00:00.00', 'Plate of bread', 1000),
       ('Fried potato', 1, '2023-04-30 10:00:00.00', 'Just fries', 1500),
       ('Green tea', 1, '2023-04-30 10:00:00.00', 'Cup of green tea with mint', 2200),
       ('Americano', 2, '2023-04-30 10:00:00.00', 'Big cup of americano', 1400),
       ('Latte', 2, '2023-04-30 10:00:00.00', 'Medium cup of latte', 2000),
       ('Espresso', 2, '2023-04-30 10:00:00.00', 'Small cup of espresso', 900),
       ('Cappuccino', 2, '2023-04-30 10:00:00.00', 'Cappuccino with oat milk', 2200),
       ('Fish and chips', 3, '2023-04-30 10:00:00.00', 'Fish and chips from Great Britain', 6500),
       ('Burger', 3, '2023-04-30 10:00:00.00', 'Big Tasty from McDonalds', 2200),
       ('Beer 0.33', 3, '2023-04-30 10:00:00.00', 'Non-alcoholic beer', 3500);

INSERT INTO VOTES (restaurant_id, user_id, date_time)
VALUES (1, 2, '2020-01-30 10:00:00.00'),
       (2, 3, '2020-01-30 10:00:00.00'),
       (3, 1, '2020-01-30 10:00:00.00');