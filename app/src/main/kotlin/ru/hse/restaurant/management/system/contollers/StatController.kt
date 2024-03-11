package ru.hse.restaurant.management.system.contollers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import ru.hse.restaurant.management.system.data.entities.Dish
import ru.hse.restaurant.management.system.services.StatService

@RestController
class StatController(private val statService: StatService) {
    @GetMapping("/stat")
    @ResponseBody
    fun getStat(): Dish {
        return statService.findMostPopularDish()
    }
}