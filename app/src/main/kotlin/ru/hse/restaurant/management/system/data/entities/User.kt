package ru.hse.restaurant.management.system.data.entities

import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import ru.hse.restaurant.management.system.enums.Role
import java.util.*

@Data
@Entity
@Table(name = "users")
@Builder
data class User(
    @Column(name = "username")
    val username: String,

    @Column(name = "password")
    val password: String,

    @Column(unique = true, name = "phone_num")
    val phoneNum: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: Role,

    @Id
    @Column(name = "id")
    private val id: UUID
)