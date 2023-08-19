CREATE TABLE reviews
(
    id               BIGSERIAL primary key,
    content          VARCHAR(100) NOT NULL,
    rating           INT          NOT NULL,
    date_of_creation TIMESTAMP,
    user_id          BIGSERIAL REFERENCES users (id),
    product_id       BIGSERIAL REFERENCES products (id)
);