package ru.hse.restaurant.management.system.data.entities

import jakarta.persistence.*
import lombok.Builder
import lombok.Data
import ru.hse.restaurant.management.system.enums.OrderStatus
import java.util.UUID

@Data
@Entity
@Table(name = "orders")
@Builder
data class Order(
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "orders_dishes",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "dish_id")]
    )
    val dishes: List<Dish>,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "orders_dishes_to_cook",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "dish_id")]
    )
    val dishesToCook: List<Dish>,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    val status: OrderStatus,

    @Column(name = "comment")
    val comment: String? = null,

    @Id
    @Column(name = "id")
    val id: UUID
)
