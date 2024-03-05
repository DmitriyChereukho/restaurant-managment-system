package ru.hse.restaurant.management.system.data.entities

import jakarta.persistence.*
import lombok.Data
import ru.hse.restaurant.management.system.enums.Role

@Data
@Entity
@Table(name = "users")
data class User(
    @Column
    val username: String,

    @Column
    val password: String,

    @Column(unique = true)
    val phoneNum: String,

    @Enumerated(EnumType.STRING)
    val role: Role,

    @Id
    val id: Int = 1
)