package ru.hse.restaurant.management.system.services

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.hse.restaurant.management.system.data.entities.Dish
import ru.hse.restaurant.management.system.data.repositories.OrderRepository

@Service
class StatService(private val orderRepository: OrderRepository) {
    fun findMostPopularDish(): Dish {
        val allOrders = orderRepository.findAll()
        val dishCounts = mutableMapOf<Dish, Int>()

        for (order in allOrders) {
            for (dish in order.dishes) {
                dishCounts[dish] = dishCounts.getOrDefault(dish, 0) + 1
            }
        }

        val mostPopularDishEntry = dishCounts.maxByOrNull { it.value }
        return mostPopularDishEntry?.key ?: throw ResponseStatusException(HttpStatus.NO_CONTENT, "No orders found")
    }
}