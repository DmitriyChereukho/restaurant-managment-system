package ru.hse.restaurant.management.system.services

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.hse.restaurant.management.system.data.entities.Order
import ru.hse.restaurant.management.system.data.repositories.OrderRepository
import ru.hse.restaurant.management.system.enums.OrderStatus
import kotlin.concurrent.thread

@Service
class RuntimeOrderService(private val orderRepository: OrderRepository) {
    private val maxThreads = 5
    private val runningThreads = mutableListOf<Thread>()

    @Synchronized
    private fun startNewThread(order: Order) {
        val newThread = thread(start = true) {
            var updatedOrder = order.copy()

            while (updatedOrder.dishesToCook.isNotEmpty()) {
                val dish = updatedOrder.dishesToCook.first()
                Thread.sleep(dish.cookingTime.toLong())
                updatedOrder = orderRepository.findByIdOrNull(updatedOrder.id)!!
                orderRepository.save(updatedOrder.copy(dishesToCook = updatedOrder.dishesToCook - dish))
            }

            orderRepository.save(updatedOrder.copy(status = OrderStatus.COMPLETED))

            synchronized(runningThreads) {
                runningThreads.remove(Thread.currentThread())
            }
        }

        synchronized(runningThreads) {
            runningThreads.add(newThread)
        }
    }

    fun processOrder(order: Order) {
        synchronized(runningThreads) {
            while (runningThreads.size >= maxThreads) {
                runningThreads.first().join()
            }
        }

        startNewThread(order)
    }
}
