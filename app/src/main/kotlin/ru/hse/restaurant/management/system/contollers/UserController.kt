package ru.hse.restaurant.management.system.contollers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.hse.restaurant.management.system.data.entities.User
import ru.hse.restaurant.management.system.data.repositories.UserRepository
import ru.hse.restaurant.management.system.dto.DtoUser
import ru.hse.restaurant.management.system.enums.Role
import ru.hse.restaurant.management.system.mappers.UserDtoToUserDataEntityMapper
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(
    @Autowired private val userRepository: UserRepository
) {

    @GetMapping("")
    fun getUsers(): List<User> =
        userRepository.findAll()

    @PostMapping("/signup")
    fun signUp(@RequestBody dtoUser: DtoUser): ResponseEntity<User> {
        return ResponseEntity.ok(
            userRepository.save(
                User(
                    "username",
                    "password",
                    "phoneNum",
                    Role.USER,
                    UUID.randomUUID()
                )
            )
        )
    }
}