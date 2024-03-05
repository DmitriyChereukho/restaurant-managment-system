package ru.hse.restaurant.management.system

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestaurantManagementSystemApplication

fun main(args: Array<String>) {
    runApplication<RestaurantManagementSystemApplication>(*args)
}
