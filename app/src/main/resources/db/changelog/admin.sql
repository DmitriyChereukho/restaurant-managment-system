--liquibase formatted sql
--changeset Dmitriy Chereukho:2
INSERT INTO users (id, username, password, phone_num, role)
VALUES ('a494b088-e627-4ed0-a550-586a210ba6f3', 'admin', '$2a$10$QjCKu1qQJbKghKwZpkmgQ.DpcTLX7ek/BIcP.AhW9Ekv1rc.096me',
        '77777777777', 'ADMIN');