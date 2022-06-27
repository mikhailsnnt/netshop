CREATE TABLE IF NOT EXISTS cart_item(
                                        user_id BIGINT NOT NULL,
                                        product_id BIGINT NOT NULL,
                                        amount INT NOT NULL,
                                        PRIMARY KEY (user_id,product_id)
    );