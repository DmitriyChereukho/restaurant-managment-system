package ru.hse.restaurant.management.system.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.hse.restaurant.management.system.data.entities.Dish

interface DishRepository : JpaRepository<Dish, Int>