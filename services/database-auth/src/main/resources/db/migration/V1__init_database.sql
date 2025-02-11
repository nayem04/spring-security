CREATE TABLE IF NOT EXISTS roles
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    created      TIMESTAMP(6)          NOT NULL,
    last_updated TIMESTAMP(6)          NOT NULL,
    description  VARCHAR(255),
    label        VARCHAR(255)          NOT NULL UNIQUE,
    name         VARCHAR(255)          NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    created                 TIMESTAMP(6)          NOT NULL,
    last_updated            TIMESTAMP(6)          NOT NULL,
    account_non_expired     BOOLEAN               NOT NULL,
    account_non_locked      BOOLEAN               NOT NULL,
    credentials_non_expired BOOLEAN               NOT NULL,
    email                   VARCHAR(255),
    enabled                 BOOLEAN               NOT NULL,
    first_name              VARCHAR(255)          NOT NULL,
    last_name               VARCHAR(255),
    password                VARCHAR(512)          NOT NULL,
    phone_number            VARCHAR(255),
    username                VARCHAR(255)          NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT fk_users_roles_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_users_roles_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

