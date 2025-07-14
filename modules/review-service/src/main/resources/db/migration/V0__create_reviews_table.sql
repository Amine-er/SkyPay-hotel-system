CREATE SCHEMA IF NOT EXISTS schema_review;

CREATE TABLE schema_review.reviews (
    id SERIAL PRIMARY KEY,
    room_id INT NOT NULL,
    user_id INT NOT NULL,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    date DATE,
    stay_duration VARCHAR(100),
    comment TEXT,
    show_more BOOLEAN DEFAULT FALSE,
    has_thumbs_up BOOLEAN DEFAULT FALSE
);
