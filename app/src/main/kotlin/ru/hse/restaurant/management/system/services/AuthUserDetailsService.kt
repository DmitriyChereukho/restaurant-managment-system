package ru.hse.restaurant.management.system.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service

@Service
class AuthUserDetailsService(private val userService: UserService) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userService.findByPhoneNum(username)?.mapToUserDetails()
            ?: throw UsernameNotFoundException("User not found")
    }

    private fun ru.hse.restaurant.management.system.data.entities.User.mapToUserDetails(): UserDetails {
        return User.builder()
            .username(this.phoneNum)
            .password(this.password)
            .roles(this.role.name)
            .build()
    }
}