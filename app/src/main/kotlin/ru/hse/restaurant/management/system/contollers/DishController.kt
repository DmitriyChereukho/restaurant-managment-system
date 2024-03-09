package ru.hse.restaurant.management.system.contollers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.hse.restaurant.management.system.dto.DtoDish
import ru.hse.restaurant.management.system.mappers.DishDtoDishDataEntityMapper
import ru.hse.restaurant.management.system.services.DishService

@RestController
@RequestMapping("/dishes")
class DishController(
    @Autowired private val dishService: DishService,
    @Autowired private val dishDtoDishDataEntityMapper: DishDtoDishDataEntityMapper
) {

    @GetMapping("")
    @ResponseBody
    fun getDishes(): List<DtoDish> {
        return dishService.getAllDishes().map {
            dishDtoDishDataEntityMapper.map(it)
        }
    }

    @PostMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun addDish(@RequestBody dish: DtoDish): DtoDish {
        val dishEntity = dishDtoDishDataEntityMapper.map(dish)
        return dishDtoDishDataEntityMapper.map(dishService.createDish(dishEntity))
    }

    @PutMapping("/{dishName}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun updateDish(@PathVariable dishName: String, @RequestBody dish: DtoDish): DtoDish {
        val dishEntity = dishDtoDishDataEntityMapper.map(dish)
        return dishDtoDishDataEntityMapper.map(dishService.updateDish(dishName, dishEntity))
    }

    @DeleteMapping("/{dishName}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun deleteDish(@PathVariable dishName: String) : DtoDish {
        return dishDtoDishDataEntityMapper.map(dishService.deleteDish(dishName))
    }
}