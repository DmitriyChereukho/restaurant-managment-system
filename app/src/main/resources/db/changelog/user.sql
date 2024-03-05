--liquibase formatted sql
--changeset Dmitriy Chereukho:1
create table "users"(
    id uuid not null constraint users_pk primary key,
    username varchar(64) not null,
    password varchar(64) not null,
    phone_num varchar(64) not null,
    role varchar(16) not null
)