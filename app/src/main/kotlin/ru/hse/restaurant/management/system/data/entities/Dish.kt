package ru.hse.restaurant.management.system.data.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Data
import java.util.*

@Data
@Entity
@Table(name = "dishes")
data class Dish(
    val name: String,

    val price: Int,

    @Id
    val id: UUID
)