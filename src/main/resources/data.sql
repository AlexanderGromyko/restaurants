INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANTS (NAME, description, enabled)
VALUES ('IKEA cafe', 'Cafe for IKEA customers', true),
       ('Cafe', 'Small cafe', true),
       ('British pub', 'Famous British pub', true),
       ('Closed pub', 'Famous British pub, but closed', false);

INSERT INTO DISHES (NAME, menu_date, restaurant_id, description, price)
VALUES ('Fried eggs', CURRENT_DATE, 1, '3 fried eggs', 4000),
       ('Bread', CURRENT_DATE, 1, 'Plate of bread', 1000),
       ('Fried potato', CURRENT_DATE, 1, 'Just fries', 1500),
       ('Green tea', CURRENT_DATE, 1, 'Cup of green tea with mint', 2200),
       ('Americano', CURRENT_DATE, 2, 'Big cup of americano', 1400),
       ('Latte', CURRENT_DATE, 2, 'Medium cup of latte', 2000),
       ('Espresso', CURRENT_DATE, 2, 'Small cup of espresso', 900),
       ('Cappuccino', CURRENT_DATE, 2, 'Cappuccino with oat milk', 2200),
       ('Fish and chips', CURRENT_DATE, 3, 'Fish and chips from Great Britain', 6500),
       ('Burger', CURRENT_DATE, 3, 'Big Tasty from McDonalds', 2200),
       ('Beer 0.33', CURRENT_DATE, 3, 'Non-alcoholic beer', 3500),
       ('Fried eggs', DATEADD('DAY', -1, CURRENT_DATE), 1, '3 fried eggs', 5000),
       ('Bread', DATEADD('DAY', -1, CURRENT_DATE), 1, 'Plate of bread', 2000),
       ('Fried potato', DATEADD('DAY', -1, CURRENT_DATE), 1, 'Just fries', 2500),
       ('Green tea', DATEADD('DAY', -1, CURRENT_DATE), 1, 'Cup of green tea with mint', 3200),
       ('Americano', DATEADD('DAY', -1, CURRENT_DATE), 2, 'Big cup of americano', 2400),
       ('Latte', DATEADD('DAY', -1, CURRENT_DATE), 2, 'Medium cup of latte', 3000),
       ('Espresso', DATEADD('DAY', -1, CURRENT_DATE), 2, 'Small cup of espresso', 1900),
       ('Cappuccino', DATEADD('DAY', -1, CURRENT_DATE), 2, 'Cappuccino with oat milk', 3200),
       ('Fish and chips', DATEADD('DAY', -1, CURRENT_DATE), 3, 'Fish and chips from Great Britain', 7500),
       ('Burger', DATEADD('DAY', -1, CURRENT_DATE), 3, 'Big Tasty from McDonalds', 3200),
       ('Beer 0.33', DATEADD('DAY', -1, CURRENT_DATE), 3, 'Non-alcoholic beer', 4500);

INSERT INTO VOTES (restaurant_id, user_id, vote_date)
VALUES (1, 3, CURRENT_DATE),
       (2, 1, CURRENT_DATE),
       (2, 1, DATEADD('DAY', -1, CURRENT_DATE)),
       (2, 2, DATEADD('DAY', -1, CURRENT_DATE)),
       (2, 3, DATEADD('DAY', -1, CURRENT_DATE)),
       (3, 1, DATEADD('DAY', -2, CURRENT_DATE)),
       (3, 2, DATEADD('DAY', -2, CURRENT_DATE)),
       (3, 3, DATEADD('DAY', -2, CURRENT_DATE));