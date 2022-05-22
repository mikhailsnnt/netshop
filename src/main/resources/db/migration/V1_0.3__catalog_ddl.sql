CREATE TABLE Catalog(
    id BIGINT PRIMARY KEY,
    parent_id BIGINT REFERENCES Catalog,
    catalog_name VARCHAR(255) NOT NULL
);
CREATE SEQUENCE IF NOT EXISTS CATALOG_SEQ START WITH 1 INCREMENT BY 1;
CREATE  INDEX IF NOT EXISTS catalog_name_idx ON Catalog(lower(catalog_name) varchar_pattern_ops); /* efficient case-independent search  */


ALTER TABLE Product ADD COLUMN catalog_id BIGINT NOT NULL  REFERENCES Catalog

