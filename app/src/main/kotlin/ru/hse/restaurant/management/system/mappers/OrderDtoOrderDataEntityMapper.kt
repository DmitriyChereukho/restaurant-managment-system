package ru.hse.restaurant.management.system.mappers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.hse.restaurant.management.system.data.entities.Order
import ru.hse.restaurant.management.system.dto.DtoOrder

@Mapper(componentModel = "spring")
interface OrderDtoOrderDataEntityMapper {
    fun map(order: Order): DtoOrder
}