package ru.hse.restaurant.management.system.services

import org.springframework.stereotype.Service
import ru.hse.restaurant.management.system.data.entities.Order
import ru.hse.restaurant.management.system.data.repositories.OrderRepository
import ru.hse.restaurant.management.system.enums.OrderStatus
import java.util.*

@Service
class OrderService(private val orderRepository: OrderRepository, private val userService: UserService) {
    fun createOrder(authHeader: String): Order {
        val user = userService.findByToken(authHeader.substringAfter("Bearer "))
        return orderRepository.save(
            Order(
                user = user,
                dishes = emptyList(),
                status = OrderStatus.CREATED,
                id = UUID.randomUUID()
            )
        )
    }

}