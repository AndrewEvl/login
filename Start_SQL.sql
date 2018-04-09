CREATE DATABASE users;
USE users;

CREATE TABLE users_table (id INT AUTO_INCREMENT,first_name VARCHAR(255), last_name VARCHAR(255), image VARCHAR(255), mail VARCHAR(255),
password VARCHAR(255), previous_status INT, status_now INT, PRIMARY KEY (id));