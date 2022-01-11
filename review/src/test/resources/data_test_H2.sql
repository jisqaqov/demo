CREATE TABLE IF NOT EXISTS reviews
(   id                   VARCHAR(300)  NOT NULL,
    average_review_score INT NOT NULL,
    reviews_number       INT NOT NULL);

INSERT INTO reviews (id, average_review_score, reviews_number)
VALUES ('BB5476.1', 12, 33),
('BB5476.2', 13, 1);
