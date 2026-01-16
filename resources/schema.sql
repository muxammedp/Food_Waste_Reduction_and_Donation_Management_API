DROP TABLE IF EXISTS donations;
DROP TABLE IF EXISTS food_items;
DROP TABLE IF EXISTS organizations;

CREATE TABLE organizations (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(255) UNIQUE
);

CREATE TABLE food_items (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255),
                            expiration_date DATE,
                            type VARCHAR(20)
                                CHECK (type IN ('PERISHABLE', 'NON_PERISHABLE'))
);

CREATE TABLE donations (
                           id SERIAL PRIMARY KEY,
                           food_item_id INT,
                           organization_id INT,

                           CONSTRAINT fk_food
                               FOREIGN KEY (food_item_id)
                                   REFERENCES food_items(id)
                                   ON DELETE CASCADE,

                           CONSTRAINT fk_org
                               FOREIGN KEY (organization_id)
                                   REFERENCES organizations(id)
                                   ON DELETE CASCADE
);
INSERT INTO organizations (name) VALUES
                                     ('City Food Bank'),
                                     ('Helping Hands Charity');

