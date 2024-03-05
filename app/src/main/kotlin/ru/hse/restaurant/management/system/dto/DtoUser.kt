package ru.hse.restaurant.management.system.dto

import lombok.Builder
import lombok.Data

@Data
@Builder
data class DtoUser(
    val username: String,
    val password: String,
    val phoneNum: String,
)
