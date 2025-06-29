CREATE TABLE IF NOT EXISTS photo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    file_name VARCHAR(255),
    content_type VARCHAR(255),
    data BLOB
);

CREATE TABLE IF NOT EXISTS product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    price FLOAT,
    quantity INTEGER
);

-- Insert sample products
INSERT INTO product (name, description, price, quantity) VALUES
('Smartphone X', 'Latest model with advanced camera', 899.99, 50),
('Wireless Earbuds', 'Noise cancelling with 24h battery', 129.99, 100),
('Smart Watch', 'Fitness tracking and notifications', 199.99, 30),
('Laptop Pro', '16GB RAM, 1TB SSD, 15.6" display', 1299.99, 20),
('Bluetooth Speaker', 'Waterproof with 20h playtime', 79.99, 75);

-- Insert sample photos (note: actual BLOB data would be binary, this is just placeholder)
-- In a real application, you would load actual image files
INSERT INTO photo (file_name, content_type, data) VALUES
('phone.jpg', 'image/jpeg', NULL),
('earbuds.jpg', 'image/jpeg', NULL),
('watch.jpg', 'image/jpeg', NULL),
('laptop.jpg', 'image/jpeg', NULL),
('speaker.jpg', 'image/jpeg', NULL);