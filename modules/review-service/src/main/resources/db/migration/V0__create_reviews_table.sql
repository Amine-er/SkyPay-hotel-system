CREATE SCHEMA IF NOT EXISTS schema_review;

CREATE TABLE schema_review.reviews (
    id SERIAL PRIMARY KEY,
    guest_name VARCHAR(100),
    guest_location VARCHAR(100),
    rating INT CHECK (rating >= 1 AND rating <= 5),
    date DATE,
    stay_duration VARCHAR(100),
    comment TEXT,
    avatar TEXT,
    years_on_platform INT,
    show_more BOOLEAN DEFAULT FALSE,
    has_thumbs_up BOOLEAN DEFAULT FALSE
);
