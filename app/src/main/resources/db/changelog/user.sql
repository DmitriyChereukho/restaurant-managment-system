--liquibase formatted sql
--changeset Dmitriy Chereukho:1
create table "users"(
    username varchar(64) not null,
    password varchar(64) not null,
    phone_num varchar(64) not null,
    role integer not null,
    id integer
)