CREATE TABLE orders
(
    id            BIGSERIAL PRIMARY KEY,
    total_cost    DECIMAL(20, 2),
    status        VARCHAR(20),
    date_of_issue TIMESTAMP,
    user_id       BIGSERIAL REFERENCES users (id)
);