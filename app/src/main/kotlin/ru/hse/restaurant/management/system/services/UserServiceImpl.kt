package ru.hse.restaurant.management.system.services

import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.hse.restaurant.management.system.data.entities.User
import ru.hse.restaurant.management.system.data.repositories.UserRepository
import ru.hse.restaurant.management.system.enums.Role

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val jwtService: JwtService
) : UserService {
    override fun findAllUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun createUser(user: User): User {
        validateUser(user)
        val updatedUser = user.copy(password = encoder.encode(user.password))
        return userRepository.save(updatedUser)
    }

    override fun findByPhoneNum(phoneNum: String): User? {
        return userRepository.findAll().find { user -> user.phoneNum == phoneNum }
    }

    override fun createAdmin(user: User): User {
        validateUser(user)
        val updatedUser = user.copy(
            password = encoder.encode(user.password),
            role = Role.ADMIN
        )
        return userRepository.save(updatedUser)
    }

    override fun findByToken(token: String): User {
        val phoneNum = jwtService.extractPhoneNum(token)
        return findByPhoneNum(phoneNum!!)!!
    }

    fun validateUser(user: User) {
        val regex = Regex("^(?:[0-9] ?){6,14}[0-9]$")
        if (user.phoneNum.isBlank() || user.password.isBlank() || !regex.matches(user.phoneNum)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user")
        }
    }
}