package ru.hse.restaurant.management.system.services

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.hse.restaurant.management.system.data.entities.Dish
import ru.hse.restaurant.management.system.data.entities.Order
import ru.hse.restaurant.management.system.data.entities.User
import ru.hse.restaurant.management.system.data.repositories.OrderRepository
import ru.hse.restaurant.management.system.enums.OrderStatus
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val runtimeOrderService: RuntimeOrderService,
    private val userService: UserService,
    private val dishService: DishService
) {
    fun createOrder(token: String): Order {
        val user = userService.findByToken(token)
        return orderRepository.save(
            Order(
                user = user,
                dishes = emptyList(),
                dishesToCook = emptyList(),
                status = OrderStatus.CREATED,
                id = UUID.randomUUID()
            )
        )
    }

    fun findCurrentOrder(user: User): Order? {
        return orderRepository.findAll()
            .find { it.user == user && (it.status == OrderStatus.CREATED || it.status == OrderStatus.IN_PROGRESS || it.status == OrderStatus.COMPLETED) }
    }

    fun addDish(token: String, dish: Dish): Order {
        dishService.validateDish(dish)
        val existingDish =
            dishService.findDish(dish.name) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Dish not found")
        val user = userService.findByToken(token)
        val order = findCurrentOrder(user) ?: createOrder(token)
        if (order.status == OrderStatus.COMPLETED) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Order is already completed")
        }
        val updatedOrder =
            orderRepository.save(
                order.copy(
                    dishes = order.dishes + existingDish,
                    dishesToCook = order.dishesToCook + existingDish
                )
            )
        if (updatedOrder.status == OrderStatus.CREATED) {
            runtimeOrderService.processOrder(updatedOrder)
            orderRepository.save(updatedOrder.copy(status = OrderStatus.IN_PROGRESS))
        }
        return updatedOrder
    }

    fun cancelOrder(token: String): Order {
        val user = userService.findByToken(token)
        val order = findCurrentOrder(user) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found")
        if (order.status != OrderStatus.CREATED && order.status != OrderStatus.IN_PROGRESS) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Order can be canceled only if it's in CREATED status"
            )
        }
        return orderRepository.save(
            order.copy(status = OrderStatus.CANCELED, dishesToCook = emptyList())
        )
    }

    fun getCurrentStatus(token: String): String {
        val user = userService.findByToken(token)

        val order = findCurrentOrder(user) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found")

        return order.status.name
    }

    fun payForOrder(token: String, sum: Int): Order {
        val user = userService.findByToken(token)
        val order = findCurrentOrder(user) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found")
        if (order.status != OrderStatus.COMPLETED) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Order can be paid only if it's in READY status"
            )
        }
        if (sum < order.dishes.sumOf { it.price }) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough money to pay for the order")
        }
        return orderRepository.save(order.copy(status = OrderStatus.PAID))
    }

    fun addComment(token: String, comment: String): Order {
        val user = userService.findByToken(token)
        val order = findCurrentOrder(user) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found")
        return orderRepository.save(order.copy(comment = comment))
    }

}