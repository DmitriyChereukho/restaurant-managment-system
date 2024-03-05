package ru.hse.restaurant.managment.system.dtos

import lombok.Builder
import lombok.Data

@Data
@Builder
data class User(
    val username: String,
    val password: String,
    val phoneNum: String,
)
