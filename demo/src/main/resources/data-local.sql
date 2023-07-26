INSERT INTO account (created_on, email, password, enabled)
values
    -- password is admin
    (NOW(), 'admin@example.com', '$2a$12$rOUvVWQDnIpJhUitE3drPeklsrCzs2NVyf/luFftt0b7kW0LiZmFq', true),
    (NOW(), 'disabled@example.com', '$2a$12$rOUvVWQDnIpJhUitE3drPeklsrCzs2NVyf/luFftt0b7kW0LiZmFq', false);