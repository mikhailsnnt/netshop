CREATE TABLE IF NOT EXISTS Cart(
    id BIGINT  PRIMARY KEY
);
CREATE SEQUENCE IF NOT EXISTS CART_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS user_address(
    id BIGINT PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    /* Using varchar for coordinates  because no operations by service will be executed with coordinates.
    We get it from third-party service(or stub) and pass to another service(or client)*/
    latitude VARCHAR(255),
    longitude VARCHAR(255),
    user_id BIGINT NOT NULL REFERENCES user_info
);
CREATE SEQUENCE IF NOT EXISTS USER_ADDRESS_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS Warehouse(
    id BIGINT PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    latitude VARCHAR(255) NOT NULL,
    longitude VARCHAR(255) NOT NULL
);
CREATE SEQUENCE IF NOT EXISTS WAREHOUSE_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS product_balance(
    warehouse_id BIGINT NOT NULL REFERENCES Warehouse,
    product_id BIGINT NOT NULL REFERENCES Product,
    amount BIGINT NOT NULL,
    PRIMARY KEY (warehouse_id, product_id)
);


CREATE TABLE IF NOT EXISTS cart_item(
    cart_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    amount INT NOT NULL,
    PRIMARY KEY (cart_id,product_id)
);

/* Name of entity -> Order.  Avoided "Order" table because of reserved keyword*/
CREATE TABLE IF NOT EXISTS order_info(
    id BIGINT  PRIMARY KEY ,
    user_id BIGINT NOT NULL REFERENCES user_info,
    destination_address_id BIGINT NOT NULL REFERENCES user_address,
    creation_time TIMESTAMP NOT NULL DEFAULT NOW(),
    completion_time TIMESTAMP,
    completion_result VARCHAR(255) /* Enum */
);

CREATE SEQUENCE IF NOT EXISTS ORDER_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS order_item(
    id BIGINT PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES order_info,
    product_id BIGINT NOT NULL REFERENCES Product,
    amount INT NOT NULL,
    price  BIGINT NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS ORDER_ITEM START WITH 1 INCREMENT BY 1;