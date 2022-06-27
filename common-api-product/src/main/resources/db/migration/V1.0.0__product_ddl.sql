CREATE TABLE Catalog
(
    id           BIGINT PRIMARY KEY,
    parent_id    BIGINT REFERENCES Catalog,
    catalog_name VARCHAR(255) NOT NULL
);
CREATE SEQUENCE IF NOT EXISTS CATALOG_SEQ START WITH 1 INCREMENT BY 1;
CREATE INDEX IF NOT EXISTS catalog_name_idx ON Catalog (lower(catalog_name) varchar_pattern_ops); /* efficient case-independent search  */


CREATE TABLE IF NOT EXISTS Product
(
    id           BIGINT PRIMARY KEY,
    product_name varchar(255) NOT NULL,
    price        BIGINT       NOT NULL,
    catalog_id  BIGINT        NOT NULL REFERENCES Catalog
);
CREATE TABLE IF NOT EXISTS product_description
(
    id          BIGINT PRIMARY KEY,
    description VARCHAR(1000) NOT NULL,
    product_id  BIGINT        NOT NULL REFERENCES Product
);
CREATE SEQUENCE IF NOT EXISTS PRODUCT_DESCRIPTION_SEQ START WITH 1 INCREMENT BY 1;
CREATE UNIQUE INDEX IF NOT EXISTS product_description_idx ON product_description (lower(description) varchar_pattern_ops); /* efficient case-independent search  */

CREATE SEQUENCE IF NOT EXISTS PRODUCT_SEQ START WITH 1 INCREMENT BY 1;
CREATE UNIQUE INDEX IF NOT EXISTS product_name_idx ON Product (lower(product_name) varchar_pattern_ops); /* efficient case-independent search  */
