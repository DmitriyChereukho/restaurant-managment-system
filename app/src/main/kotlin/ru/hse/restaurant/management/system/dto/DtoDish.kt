package ru.hse.restaurant.management.system.dto

import lombok.Builder
import lombok.Data

@Data
@Builder
class DtoDish(
    val name: String,
    val price: Int,
    val cookingTime: Int
)