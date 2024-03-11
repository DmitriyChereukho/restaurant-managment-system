package ru.hse.restaurant.management.system.mappers

import org.mapstruct.*
import ru.hse.restaurant.management.system.data.entities.Dish
import ru.hse.restaurant.management.system.dto.DtoDish

@Mapper(componentModel = "spring")
interface DishDtoDishDataEntityMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    fun map(dtoDish: DtoDish): Dish

    @InheritInverseConfiguration
    fun map(dish: Dish): DtoDish
}