-- CREATE SCHEMA IF NOT EXISTS migrations;

CREATE TABLE IF NOT EXISTS users (
    id INT,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    active BOOLEAN NOT NULL,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    super_user BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS stocks (
    id INT,
    name TEXT NOT NULL,
    current_price FLOAT NOT NULL,
    currency_id TEXT NOT NULL,
    last_update TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN NOT NULL,
    quantity INT4 NOT NULL,
    user_id INT,
    PRIMARY KEY (id),
       CONSTRAINT fk_user
          FOREIGN KEY(user_id)
    	  REFERENCES users(id)
    	  ON DELETE SET NULL
);

-- INSERT DATA
INSERT INTO users (id, first_name, last_name, active, username, password, super_user)
VALUES
    (1, 'admin', 'admin', True, 'admin', 'admin', True),
    (2, 'viewer', 'viewer', True, 'viewer', 'viewer', False);