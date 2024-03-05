package ru.hse.restaurant.management.system.contollers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.hse.restaurant.management.system.data.entities.User
import ru.hse.restaurant.management.system.data.repositories.UserRepository
import ru.hse.restaurant.management.system.enums.Role.USER

@RestController
@RequestMapping("/users")
class UserController(@Autowired private val userRepository: UserRepository) {

    @GetMapping("")
    fun getUsers(): List<User> =
        userRepository.findAll()

    @PostMapping("/signup")
    fun signUp(
        @RequestParam("username") username: String,
        @RequestParam("password") password: String,
        @RequestParam("phoneNum") phoneNum: String
    ): ResponseEntity<User> {
        val user = User(username, password, phoneNum, USER, 1)
        return ResponseEntity.ok(userRepository.save(user))
    }
}