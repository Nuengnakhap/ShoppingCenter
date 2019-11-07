DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_detail;
 
CREATE TABLE product (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  price DOUBLE NOT NULL
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id VARCHAR(250) UNIQUE NOT NULL,
    order_num INT DEFAULT 1
);

CREATE TABLE order_detail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id VARCHAR(250) NOT NULL,
    quanity INT NOT NULL,
    price DOUBLE NOT NULL,
    product_id INT NOT NULL,
    orderDate VARCHAR(250) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (customer_id) REFERENCES orders(customer_id)
);
 
INSERT INTO product (name, description, price) VALUES
  ('FLAPPER VEGETABLE MIX DELIGHTS 70 GM', 'Vegetable chips are chips or crisps that are prepared using vegetables. Vegetable chips may be fried, deep-fried, dehydrated, dried or baked. Many various root vegetables or leaf vegetables are used.', 10),
  ('NICK SNACKS BABY APPLE STARS 30 GM', 'Freshly baked cookies are the stuff of childhood dreams, help the kids make their own with these simple.', 10),
  ('CHI ORGANIC VIRGIN COCONUT OIL 200 ML', 'Chi 100% Organic Virgin coconut oil is never heated.', 10),
  ('LA CUERVA BLACKBERRIES 300 GM', 'The blackberry is an aggregate fruit that.The fruit is very dark purple with smooth, fragile skin. In the center of the cluster is a greenish-white core that extends to almost the bottom of the berry. Blackberries are red and hard when they are immature and turn black and shiny when they ripen.', 10),
  ('COCOMOJO PASSION PURE COCONUT WATER DRINK 250 ML', 'A delightful and delicious pure coconut water drink blended with a burst of passion fruit.', 10);