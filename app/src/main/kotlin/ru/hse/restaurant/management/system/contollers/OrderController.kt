package ru.hse.restaurant.management.system.contollers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.hse.restaurant.management.system.dto.DtoDish
import ru.hse.restaurant.management.system.dto.DtoOrder
import ru.hse.restaurant.management.system.mappers.DishDtoDishDataEntityMapper
import ru.hse.restaurant.management.system.mappers.OrderDtoOrderDataEntityMapper
import ru.hse.restaurant.management.system.services.OrderService

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService,
    private val orderDtoOrderDataEntityMapper: OrderDtoOrderDataEntityMapper,
    private val dishDtoDishDataEntityMapper: DishDtoDishDataEntityMapper
) {
    @PostMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestHeader("Authorization") authHeader: String): DtoOrder {
        return orderDtoOrderDataEntityMapper.map(orderService.createOrder(authHeader.substringAfter("Bearer ")))
    }

    @PutMapping("")
    @ResponseBody
    fun addDish(@RequestHeader("Authorization") authHeader: String, @RequestBody dish: DtoDish): DtoOrder {
        return orderDtoOrderDataEntityMapper.map(orderService.addDish(authHeader.substringAfter("Bearer "), dishDtoDishDataEntityMapper.map(dish)))
    }

    @DeleteMapping("")
    @ResponseBody
    fun cancelOrder(@RequestHeader("Authorization") authHeader: String): DtoOrder {
        return orderDtoOrderDataEntityMapper.map(orderService.cancelOrder(authHeader.substringAfter("Bearer ")))
    }

    @GetMapping("")
    @ResponseBody
    fun getCurrentStatus(@RequestHeader("Authorization") authHeader: String): String {
        return orderService.getCurrentStatus(authHeader.substringAfter("Bearer "))
    }

    @PutMapping("/pay")
    @ResponseBody
    fun payForOrder(@RequestHeader("Authorization") authHeader: String, @RequestBody sum : Int): DtoOrder {
        return orderDtoOrderDataEntityMapper.map(orderService.payForOrder(authHeader.substringAfter("Bearer "), sum))
    }

    @PutMapping("/comment")
    @ResponseBody
    fun leaveComment(@RequestHeader("Authorization") authHeader: String, @RequestBody comment: String): DtoOrder {
        return orderDtoOrderDataEntityMapper.map(orderService.addComment(authHeader.substringAfter("Bearer "), comment))
    }


}