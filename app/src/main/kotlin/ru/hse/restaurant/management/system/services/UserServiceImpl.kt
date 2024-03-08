package ru.hse.restaurant.management.system.services

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.hse.restaurant.management.system.data.entities.User
import ru.hse.restaurant.management.system.data.repositories.UserRepository
import ru.hse.restaurant.management.system.enums.Role

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) : UserService {
    override fun findAllUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun createUser(user: User): User {
        val updatedUser = user.copy(password = encoder.encode(user.password))
        return userRepository.save(updatedUser)
    }

    override fun findByPhoneNum(phoneNum: String): User? {
        return userRepository.findAll().find { user -> user.phoneNum == phoneNum }
    }

    override fun createAdmin(user: User): User {
        val updatedUser = user.copy(
            password = encoder.encode(user.password),
            role = Role.ADMIN
        )
        return userRepository.save(updatedUser)
    }
}