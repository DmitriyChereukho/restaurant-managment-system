package ru.hse.restaurant.management.system.mappers

import org.mapstruct.*
import ru.hse.restaurant.management.system.data.entities.User
import ru.hse.restaurant.management.system.dto.DtoUser

@Mapper(componentModel = "spring")
interface UserDtoToUserDataEntityMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "role", expression = "java(ru.hse.restaurant.management.system.enums.Role.USER)")
    fun map(dtoUser: DtoUser): User
}