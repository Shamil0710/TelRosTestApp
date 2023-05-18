--drop table if exists user cascade;

--changeset shamil:001

CREATE DATABASE users;
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       last_name VARCHAR(50) NOT NULL,
                       first_name VARCHAR(50) NOT NULL,
                       middle_name VARCHAR(50),
                       birth_date DATE,
                       email VARCHAR(50) UNIQUE NOT NULL,
                       phone VARCHAR(20),
                       photo BYTEA
);