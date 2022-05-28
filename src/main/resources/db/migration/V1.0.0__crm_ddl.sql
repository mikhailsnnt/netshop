/* In future, migration to separate storage is likely */
CREATE TABLE IF NOT EXISTS Credentials(
                                          id BIGINT PRIMARY KEY,
                                          credentials VARCHAR(255)
);
CREATE SEQUENCE IF NOT EXISTS CREDENTIALS_SEQ start with 1 increment by 1;

CREATE TABLE IF NOT EXISTS Cart(
    id BIGINT  PRIMARY KEY
);
CREATE SEQUENCE IF NOT EXISTS CART_SEQ START WITH 1 INCREMENT BY 1;
/* Name of entity -> User.  Avoided "User" table because of reserved keyword*/
CREATE TABLE IF NOT EXISTS user_info
(
    id BIGINT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_surname VARCHAR(255) NOT NULL ,
    user_patronymic VARCHAR(255),
    phone_number VARCHAR(16) NOT NULL UNIQUE,
    email VARCHAR(255) UNIQUE,
    credentials_id BIGINT NOT NULL REFERENCES Credentials ON DELETE CASCADE,
    cart_id BIGINT NOT NULL REFERENCES Cart ON DELETE CASCADE
);
CREATE  UNIQUE INDEX IF NOT EXISTS phone_number_idx ON user_info (phone_number);
CREATE  UNIQUE INDEX IF NOT EXISTS email_idx ON user_info (email);

CREATE SEQUENCE IF NOT EXISTS USER_SEQ START WITH 1 INCREMENT BY 1;

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



CREATE TABLE IF NOT EXISTS Product(
    id BIGINT PRIMARY KEY,
    product_name varchar(255) NOT NULL,
    price BIGINT NOT NULL
);
CREATE TABLE IF NOT EXISTS product_description(
    id BIGINT PRIMARY KEY,
    description VARCHAR(1000) NOT NULL,
    product_id BIGINT NOT NULL  REFERENCES Product
);
CREATE SEQUENCE IF NOT EXISTS PRODUCT_DESCRIPTION_SEQ START WITH 1 INCREMENT BY 1;
CREATE  UNIQUE INDEX IF NOT EXISTS product_description_idx ON product_description(lower(description) varchar_pattern_ops); /* efficient case-independent search  */

CREATE SEQUENCE IF NOT EXISTS PRODUCT_SEQ START WITH 1 INCREMENT BY 1;
CREATE  UNIQUE INDEX IF NOT EXISTS product_name_idx ON Product(lower(product_name) varchar_pattern_ops); /* efficient case-independent search  */

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