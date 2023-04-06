DROP TABLE IF EXISTS movements;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS franchises;
DROP TABLE IF EXISTS users;

CREATE TABLE categories (
	
	id CHAR(36) NOT NULL,
	name VARCHAR(100) NOT NULL,
	
	PRIMARY KEY (id)
	
);

CREATE TABLE franchises (
	
	id CHAR(36) NOT NULL,
	name VARCHAR(100) NOT NULL,
	
	PRIMARY KEY (id)
	
);

CREATE TABLE products (

	id CHAR(36) NOT NULL,
	code VARCHAR(50) NOT NULL,
	name VARCHAR(150) NOT NULL,
	purchase_price DECIMAL(10,2) NOT NULL,
	selling_price DECIMAL(10,2) NOT NULL,
	category_id CHAR(36) NOT NULL,
	franchise_id CHAR(36) NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY (category_id) REFERENCES categories (id),
	FOREIGN KEY (franchise_id) REFERENCES franchises (id)
	
);

CREATE TABLE movements (

	id CHAR(36) NOT NULL,
	product_id CHAR(36) NOT NULL,
	type VARCHAR(20) NOT NULL,
	quantity INTEGER NOT NULL,
	price DECIMAL (10,2) NOT NULL,
	observation VARCHAR(250),
	created_at TIMESTAMP NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY (product_id) REFERENCES products (id)

);

CREATE TABLE users (

	id CHAR(36) NOT NULL,
	username VARCHAR(100) NOT NULL,
	password CHAR(60) NOT NULL,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	
	PRIMARY KEY (id)

);