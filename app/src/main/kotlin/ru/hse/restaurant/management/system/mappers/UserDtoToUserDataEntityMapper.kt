package ru.hse.restaurant.management.system.mappers

import lombok.AllArgsConstructor
import org.mapstruct.*
import org.springframework.stereotype.Component
import ru.hse.restaurant.management.system.data.entities.User
import ru.hse.restaurant.management.system.dto.DtoUser


@Mapper
@AllArgsConstructor
interface UserDtoToUserDataEntityMapper {
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "phoneNum", target = "phoneNum")
    fun map(dtoUser: DtoUser): User
}