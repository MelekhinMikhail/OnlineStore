CREATE TABLE products
(
    id                BIGSERIAL PRIMARY KEY,
    title             VARCHAR(50) NOT NULL,
    description       VARCHAR(150),
    price             DECIMAL(20, 2),
    image_path        VARCHAR(100),
    quantity_in_stock INT,
    rating            DECIMAL(1, 1),
    date_added        TIMESTAMP,
    last_update       TIMESTAMP,
    user_id           BIGSERIAL REFERENCES users (id)
);