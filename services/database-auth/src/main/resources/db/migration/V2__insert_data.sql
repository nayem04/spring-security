-- Insert sample roles
INSERT INTO roles (created, last_updated, description, label, name)
VALUES (NOW(), NOW(), 'Administrator role', 'Admin', 'ROLE_ADMIN'),
       (NOW(), NOW(), 'Regular user role', 'User', 'ROLE_USER'),
       (NOW(), NOW(), 'Manager role', 'Manager', 'ROLE_MANAGER')
ON CONFLICT (name) DO NOTHING;
-- Prevents duplicate insertions

-- Insert sample users
INSERT INTO users (created, last_updated, account_non_expired, account_non_locked, credentials_non_expired, email,
                   enabled, first_name, last_name, password, phone_number, username)
VALUES (NOW(), NOW(), TRUE, TRUE, TRUE, 'admin@example.com', TRUE, 'Admin', 'User', 'hashed_password_1', '01700000000',
        'admin'),
       (NOW(), NOW(), TRUE, TRUE, TRUE, 'user@example.com', TRUE, 'Regular', 'User', 'hashed_password_2', '01700000001',
        'user'),
       (NOW(), NOW(), TRUE, TRUE, TRUE, 'manager@example.com', TRUE, 'Manager', 'User', 'hashed_password_3', '01700000002',
        'manager')
ON CONFLICT (username) DO NOTHING;
-- Prevents duplicate insertions

-- Assign roles to users
-- Assuming role IDs are auto-generated, we retrieve them dynamically
INSERT INTO users_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u,
     roles r
WHERE u.username = 'admin'
  AND r.name = 'ROLE_ADMIN'
ON CONFLICT DO NOTHING;

INSERT INTO users_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u,
     roles r
WHERE u.username = 'user'
  AND r.name = 'ROLE_USER'
ON CONFLICT DO NOTHING;

INSERT INTO users_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u,
     roles r
WHERE u.username = 'manager'
  AND r.name = 'ROLE_MANAGER'
ON CONFLICT DO NOTHING;
