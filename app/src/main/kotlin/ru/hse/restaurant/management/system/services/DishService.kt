package ru.hse.restaurant.management.system.services

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.hse.restaurant.management.system.data.entities.Dish
import ru.hse.restaurant.management.system.data.repositories.DishRepository

@Service
class DishService(private val dishRepository: DishRepository) {
    fun getAllDishes(): List<Dish> {
        return dishRepository.findAll()
    }

    fun createDish(dish: Dish): Dish {
        return dishRepository.save(dish)
    }

    fun updateDish(dishName: String, dish: Dish): Dish {
        return dishRepository.findAll().find { it.name == dishName }?.let {
            dishRepository.save(
                it.copy(
                    name = dish.name,
                    price = dish.price,
                    cookingTime = dish.cookingTime
                )
            )
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Dish not found")
    }

    fun deleteDish(dishName: String): Dish {
        return dishRepository.findAll().find { it.name == dishName }?.let {
            dishRepository.delete(it)
            it
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Dish not found")
    }
}