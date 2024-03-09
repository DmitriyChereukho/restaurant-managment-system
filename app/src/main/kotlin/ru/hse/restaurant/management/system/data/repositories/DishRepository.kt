package ru.hse.restaurant.management.system.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.hse.restaurant.management.system.data.entities.Dish
import java.util.UUID

interface DishRepository : JpaRepository<Dish, UUID>