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

INSERT INTO DISHES (NAME, restaurant_id, description, price)
VALUES ('Fried eggs', 1, '3 fried eggs', 4000),
       ('Bread', 1, 'Plate of bread', 1000),
       ('Fried potato', 1, 'Just fries', 1500),
       ('Green tea', 1, 'Cup of green tea with mint', 2200),
       ('Americano', 2, 'Big cup of americano', 1400),
       ('Latte', 2, 'Medium cup of latte', 2000),
       ('Espresso', 2, 'Small cup of espresso', 900),
       ('Cappuccino', 2, 'Cappuccino with oat milk', 2200),
       ('Fish and chips', 3, 'Fish and chips from Great Britain', 6500),
       ('Burger', 3, 'Big Tasty from McDonalds', 2200),
       ('Beer 0.33', 3, 'Non-alcoholic beer', 3500);

INSERT INTO VOTES (restaurant_id, user_id, vote_date)
VALUES (1, 2, CURRENT_DATE),
       (1, 3, CURRENT_DATE),
       (2, 1, CURRENT_DATE),
       (2, 1, DATEADD('DAY',-1, CURRENT_DATE));