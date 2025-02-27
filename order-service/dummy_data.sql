


-- 生成 10 条随机Address数据
INSERT INTO address (country, state, city, street, zipcode)
VALUES
    ('USA', 'California', 'Los Angeles', '1234 Sunset Blvd', FLOOR(10000 + (RAND() * 9999))),
    ('Canada', 'Ontario', 'Toronto', '5678 Queen St', FLOOR(10000 + (RAND() * 9999))),
    ('Germany', 'Bavaria', 'Munich', '7890 Leopoldstrasse', FLOOR(10000 + (RAND() * 9999))),
    ('UK', 'England', 'London', '1012 Baker Street', FLOOR(10000 + (RAND() * 9999))),
    ('USA', 'New York', 'New York', '2345 Broadway', FLOOR(10000 + (RAND() * 9999))),
    ('Australia', 'New South Wales', 'Sydney', '6789 George St', FLOOR(10000 + (RAND() * 9999))),
    ('France', 'Île-de-France', 'Paris', '4321 Rue de Rivoli', FLOOR(10000 + (RAND() * 9999))),
    ('Japan', 'Tokyo', 'Tokyo', '1357 Shibuya', FLOOR(10000 + (RAND() * 9999))),
    ('China', 'Beijing', 'Beijing', '2468 Wangfujing St', FLOOR(10000 + (RAND() * 9999))),
    ('India', 'Maharashtra', 'Mumbai', '8901 Colaba', FLOOR(10000 + (RAND() * 9999)));

-- 生成 10 条随机订单项数据
INSERT INTO order_item (productId, order_id, quantity, cost)
VALUES
    (FLOOR(1 + (RAND() * 50)), FLOOR(1 + (RAND() * 10)), FLOOR(1 + (RAND() * 5)), FLOOR(10 + (RAND() * 100))),
    (FLOOR(1 + (RAND() * 50)), FLOOR(1 + (RAND() * 10)), FLOOR(1 + (RAND() * 5)), FLOOR(10 + (RAND() * 100))),
    (FLOOR(1 + (RAND() * 50)), FLOOR(1 + (RAND() * 10)), FLOOR(1 + (RAND() * 5)), FLOOR(10 + (RAND() * 100))),
    (FLOOR(1 + (RAND() * 50)), FLOOR(1 + (RAND() * 10)), FLOOR(1 + (RAND() * 5)), FLOOR(10 + (RAND() * 100))),
    (FLOOR(1 + (RAND() * 50)), FLOOR(1 + (RAND() * 10)), FLOOR(1 + (RAND() * 5)), FLOOR(10 + (RAND() * 100))),
    (FLOOR(1 + (RAND() * 50)), FLOOR(1 + (RAND() * 10)), FLOOR(1 + (RAND() * 5)), FLOOR(10 + (RAND() * 100))),
    (FLOOR(1 + (RAND() * 50)), FLOOR(1 + (RAND() * 10)), FLOOR(1 + (RAND() * 5)), FLOOR(10 + (RAND() * 100))),
    (FLOOR(1 + (RAND() * 50)), FLOOR(1 + (RAND() * 10)), FLOOR(1 + (RAND() * 5)), FLOOR(10 + (RAND() * 100))),
    (FLOOR(1 + (RAND() * 50)), FLOOR(1 + (RAND() * 10)), FLOOR(1 + (RAND() * 5)), FLOOR(10 + (RAND() * 100))),
    (FLOOR(1 + (RAND() * 50)), FLOOR(1 + (RAND() * 10)), FLOOR(1 + (RAND() * 5)), FLOOR(10 + (RAND() * 100)));


-- 生成 10 条随机订单数据
INSERT INTO `order` (userId, address_id, status, create_time, update_time)
VALUES
    (FLOOR(1 + (RAND() * 100)), FLOOR(1 + (RAND() * 10)), 'PENDING', NOW(), NOW()),
    (FLOOR(1 + (RAND() * 100)), FLOOR(1 + (RAND() * 10)), 'SHIPPED', NOW(), NOW()),
    (FLOOR(1 + (RAND() * 100)), FLOOR(1 + (RAND() * 10)), 'DELIVERED', NOW(), NOW()),
    (FLOOR(1 + (RAND() * 100)), FLOOR(1 + (RAND() * 10)), 'CANCELLED', NOW(), NOW()),
    (FLOOR(1 + (RAND() * 100)), FLOOR(1 + (RAND() * 10)), 'PENDING', NOW(), NOW()),
    (FLOOR(1 + (RAND() * 100)), FLOOR(1 + (RAND() * 10)), 'SHIPPED', NOW(), NOW()),
    (FLOOR(1 + (RAND() * 100)), FLOOR(1 + (RAND() * 10)), 'DELIVERED', NOW(), NOW()),
    (FLOOR(1 + (RAND() * 100)), FLOOR(1 + (RAND() * 10)), 'PENDING', NOW(), NOW()),
    (FLOOR(1 + (RAND() * 100)), FLOOR(1 + (RAND() * 10)), 'CANCELLED', NOW(), NOW()),
    (FLOOR(1 + (RAND() * 100)), FLOOR(1 + (RAND() * 10)), 'PENDING', NOW(), NOW());
