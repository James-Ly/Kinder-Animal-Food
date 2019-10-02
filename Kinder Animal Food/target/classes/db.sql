CREATE DATABASE IF NOT EXISTS kinder_animal_food;
USE kinder_animal_food;
DROP TABLE IF EXISTS Admin;
CREATE TABLE Admin(
admin_id VARCHAR(10) NOT NULL,
admin_password VARCHAR(255) NOT NULL,
PRIMARY KEY (admin_id)
);
INSERT INTO Admin VALUES ("admin","admin");