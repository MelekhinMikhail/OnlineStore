CREATE TABLE users
(
    id                BIGSERIAL PRIMARY KEY,
    email             VARCHAR(50) NOT NULL UNIQUE,
    password          VARCHAR     NOT NULL,
    user_role         VARCHAR(20) NOT NULL,
    date_of_birth     TIMESTAMP,
    first_name        VARCHAR(30) NOT NULL,
    last_name         VARCHAR(30),
    requisites        VARCHAR(100),
    image_path        VARCHAR(100),
    address           VARCHAR(100),
    registration_date TIMESTAMP,
    last_update       TIMESTAMP
);