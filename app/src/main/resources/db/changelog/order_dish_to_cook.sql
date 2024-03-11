--liquibase formatted sql
--changeset Dmitriy Chereukho:6
create table "orders_dishes_to_cook"(
    order_id uuid not null constraint fk_orders_dishes_to_cook_order references orders (id),
    dish_id uuid not null constraint fk_orders_dishes_to_cook_dish references dishes (id),
    constraint uq_orders_dishes_to_cook_order_id_dish_id unique (order_id, dish_id)
)