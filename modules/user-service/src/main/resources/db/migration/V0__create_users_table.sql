CREATE SCHEMA IF NOT EXISTS schemauser;

CREATE TABLE schemauser.users (
        id SERIAL PRIMARY KEY,
        first_name VARCHAR(100),
        last_name VARCHAR(100),
        email VARCHAR(255) UNIQUE NOT NULL,
        phone VARCHAR(50),
        address TEXT
);