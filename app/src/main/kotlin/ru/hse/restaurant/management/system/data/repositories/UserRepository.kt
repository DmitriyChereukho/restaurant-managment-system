package ru.hse.restaurant.management.system.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.hse.restaurant.management.system.data.entities.User

interface UserRepository : JpaRepository<User, Int>