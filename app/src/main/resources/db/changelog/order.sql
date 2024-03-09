--liquibase formatted sql
--changeset Dmitriy Chereukho:4
create table "orders"(
     id uuid not null constraint orders_pk primary key,
     user_id uuid not null,
     status varchar(64) not null
)