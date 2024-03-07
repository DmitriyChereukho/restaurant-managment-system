package ru.hse.restaurant.management.system.services

import ru.hse.restaurant.management.system.data.entities.User

interface UserService {
    fun findAllUsers() : List<User>

    fun createUser(user: User) : User

    fun findByPhoneNum(phoneNum: String) : User?
}