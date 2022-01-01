-- CREATE SCHEMA IF NOT EXISTS migrations;

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    active BOOLEAN NOT NULL,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    super_user BOOLEAN NOT NULL,
    roles TEXT NULL
);

ALTER TABLE users ALTER COLUMN active SET DEFAULT TRUE;

CREATE TABLE IF NOT EXISTS stocks (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    current_price FLOAT NOT NULL,
    currency_id TEXT NOT NULL,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    quantity INT4 NOT NULL,
    user_id INT,
       CONSTRAINT fk_user
          FOREIGN KEY(user_id)
    	  REFERENCES users(id)
    	  ON DELETE SET NULL
);

ALTER TABLE stocks ALTER COLUMN is_active SET DEFAULT TRUE;
ALTER TABLE stocks ALTER COLUMN last_update SET DEFAULT CURRENT_TIMESTAMP;

-- INSERT DATA
INSERT INTO users (id, first_name, last_name, active, username, password, super_user, roles)
VALUES
    (1, 'admin', 'admin', True, 'admin', 'admin', True, 'admin'),
    (2, 'viewer', 'viewer', True, 'viewer', 'viewer', False, 'user');

--