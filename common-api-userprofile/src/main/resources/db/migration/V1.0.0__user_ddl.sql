/* In future, migration to separate storage is likely */
CREATE TABLE IF NOT EXISTS Credentials(
                                          id BIGINT PRIMARY KEY,
                                          credentials VARCHAR(255)
);
CREATE SEQUENCE IF NOT EXISTS CREDENTIALS_SEQ start with 1 increment by 1;

CREATE TABLE IF NOT EXISTS user_info
(
    id BIGINT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_surname VARCHAR(255) NOT NULL ,
    user_patronymic VARCHAR(255),
    phone_number VARCHAR(16) NOT NULL UNIQUE,
    email VARCHAR(255) UNIQUE,
    credentials_id BIGINT NOT NULL REFERENCES Credentials ON DELETE CASCADE
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

CREATE TABLE IF NOT EXISTS Role(
                                   id BIGINT PRIMARY KEY,
                                   role_name VARCHAR(255) NOT NULL UNIQUE
    );
CREATE SEQUENCE IF NOT EXISTS ROLE_SEQ START WITH 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS user_roles(
                                         user_id BIGINT NOT NULL REFERENCES user_info,
                                         role_id BIGINT NOT NULL REFERENCES Role,
                                         UNIQUE (user_id, role_id)
    )
