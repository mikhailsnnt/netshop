CREATE TABLE Catalog(
    id BIGINT PRIMARY KEY,
    parent_id BIGINT REFERENCES Catalog,
    name VARCHAR(255) NOT NULL
);
CREATE SEQUENCE IF NOT EXISTS CATALOG_SEQ START WITH 1 INCREMENT BY 1;

ALTER TABLE Product ADD COLUMN catalog_id BIGINT NOT NULL  REFERENCES Catalog
