package ru.hse.restaurant.management.system.data.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Builder
import lombok.Data
import java.util.*

@Data
@Entity
@Table(name = "dishes")
@Builder
data class Dish(
    @Column(name = "name", unique = true)
    val name: String,

    @Column(name = "price")
    val price: Int,

    @Column(name = "cooking_time")
    val cookingTime: Int,

    @Id
    @Column(name = "id")
    private val id: UUID
)