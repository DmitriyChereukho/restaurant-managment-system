--liquibase formatted sql
--changeset Dmitriy Chereukho:5
create table "orders_dishes"(
     order_id uuid not null constraint fk_orders_dishes_order references orders (id),
     dish_id uuid not null constraint fk_orders_dishes_dish references dishes (id),
     constraint uq_orders_dishes_order_id_dish_id unique (order_id, dish_id)
)