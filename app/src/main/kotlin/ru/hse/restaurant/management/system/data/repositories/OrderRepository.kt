package ru.hse.restaurant.management.system.data.repositories

import org.springframework.data.jpa.repository.JpaRepository
import ru.hse.restaurant.management.system.data.entities.Order
import java.util.*

interface OrderRepository : JpaRepository<Order, UUID>