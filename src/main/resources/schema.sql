CREATE TABLE IF NOT EXISTS photo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    file_name VARCHAR(255),
    content_type VARCHAR(255),
    data BLOB
);

CREATE TABLE IF NOT EXISTS PRODUCTS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    price FLOAT,
    quantity INTEGER
);

CREATE TABLE IF NOT EXISTS ORDERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NULL,
    customer_email VARCHAR(255) NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'PENDING',
    total_amount FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS ORDERS_PRODUCTS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    price_at_purchase FLOAT NOT NULL,
    -- PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE RESTRICT
);

-- Insert sample products
INSERT INTO PRODUCTS (name, description, price, quantity) VALUES
('iPhone 15 Pro', '6.1-inch Super Retina XDR display', 999.00, 100),
('MacBook Pro 14"', 'M3 Pro chip, 16GB RAM, 512GB SSD', 1999.00, 50),
('AirPods Pro (2nd Gen)', 'Active Noise Cancellation', 249.00, 200),
('Apple Watch Series 9', '45mm GPS model', 429.00, 75),
('iPad Air', 'M1 chip, 10.9-inch Liquid Retina display', 599.00, 60);

-- Insert sample orders
INSERT INTO ORDERS (customer_name, customer_email, status, total_amount) VALUES
('John Smith', 'john.smith@example.com', 'COMPLETED', 1248.00),
('Emily Johnson', 'emily.j@example.com', 'SHIPPED', 2998.00),
('Michael Brown', 'michael.b@example.com', 'PENDING', 678.00),
('Sarah Davis', 'sarah.d@example.com', 'PROCESSING', 599.00);

-- Insert order items (products in each order)
INSERT INTO ORDERS_PRODUCTS (order_id, product_id, quantity, price_at_purchase) VALUES
-- Order 1: John's order (iPhone + AirPods)
(1, 1, 1, 999.00),
(1, 3, 1, 249.00),

-- Order 2: Emily's order (MacBook Pro + Apple Watch)
(2, 2, 1, 1999.00),
(2, 4, 1, 429.00),
(2, 3, 2, 249.00),  -- Two AirPods

-- Order 3: Michael's order (AirPods)
(3, 3, 1, 249.00),
(3, 5, 1, 429.00),  -- Watch at different price

-- Order 4: Sarah's order (iPad)
(4, 5, 1, 599.00);


-- Get all orders with their items
--SELECT o.id, o.customer_name, p.name, op.quantity, op.price_at_purchase
--FROM orders o
--JOIN orders_products op ON o.id = op.order_id
--JOIN product p ON op.product_id = p.id;
--
---- Check product inventory after orders
--SELECT p.id, p.name, p.quantity,
--       (SELECT SUM(op.quantity)
--        FROM orders_products op
--        WHERE op.product_id = p.id) AS ordered_quantity
--FROM product p;