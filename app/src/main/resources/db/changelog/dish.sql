--liquibase formatted sql
--changeset Dmitriy Chereukho:3
create table "dishes"(
    id uuid not null constraint dishes_pk primary key,
    name varchar(64) not null,
    price integer not null,
    cooking_time integer not null
)