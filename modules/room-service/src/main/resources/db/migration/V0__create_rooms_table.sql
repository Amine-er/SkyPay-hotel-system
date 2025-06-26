CREATE SCHEMA IF NOT EXISTS schemaroom;

CREATE TABLE schemaroom.rooms (
      id BIGSERIAL PRIMARY KEY,
      type VARCHAR(50) NOT NULL,
      description TEXT,
      image_url TEXT[] NOT NULL,
      price NUMERIC(19,2) NOT NULL
);