package ru.hse.restaurant.management.system.contollers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.hse.restaurant.management.system.dto.DtoOrder
import ru.hse.restaurant.management.system.mappers.OrderDtoOrderDataEntityMapper
import ru.hse.restaurant.management.system.services.OrderService

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService,
    private val orderDtoOrderDataEntityMapper: OrderDtoOrderDataEntityMapper
) {
    @PostMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestHeader("Authorization") authHeader: String): DtoOrder {
        return orderDtoOrderDataEntityMapper.map(orderService.createOrder(authHeader))
    }
}