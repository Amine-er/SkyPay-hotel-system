CREATE SCHEMA IF NOT EXISTS schemaroom;

CREATE TABLE schemaroom.rooms (
      id BIGSERIAL PRIMARY KEY,
      type VARCHAR(50) NOT NULL,
      description TEXT,
      image_url VARCHAR(255),
      price NUMERIC(19,2) NOT NULL
);