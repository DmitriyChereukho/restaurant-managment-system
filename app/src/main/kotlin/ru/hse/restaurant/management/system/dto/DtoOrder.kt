package ru.hse.restaurant.management.system.dto

import lombok.Builder
import lombok.Data

@Data
@Builder
data class DtoOrder(
    val user: DtoUser,
    val dishes: List<DtoDish>,
    val status: String,
    val comment: String?
)
