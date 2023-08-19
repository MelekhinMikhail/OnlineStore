CREATE TABLE order_items
(
    id BIGSERIAL PRIMARY KEY,
    quantity INT,
    product_id BIGSERIAL REFERENCES products (id),
    order_id BIGSERIAL REFERENCES orders (id)
);