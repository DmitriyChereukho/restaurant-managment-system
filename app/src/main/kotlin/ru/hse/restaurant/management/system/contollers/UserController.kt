package ru.hse.restaurant.management.system.contollers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.hse.restaurant.management.system.data.entities.User
import ru.hse.restaurant.management.system.dto.DtoUser
import ru.hse.restaurant.management.system.mappers.UserDtoUserDataEntityMapper
import ru.hse.restaurant.management.system.services.UserService

@RestController
@RequestMapping("/users")
class UserController(
    @Autowired private val userService: UserService,
    @Autowired private val userDtoUserDataEntityMapper: UserDtoUserDataEntityMapper
) {

    @GetMapping("")
    @ResponseBody
    fun getUsers(): List<User> = userService.findAllUsers()

    @PostMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: DtoUser): DtoUser {
        val userEntity = userDtoUserDataEntityMapper.map(user)
        return userDtoUserDataEntityMapper.map(userService.createUser(userEntity))
    }
}