package ru.hse.restaurant.management.system.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import ru.hse.restaurant.management.system.data.repositories.UserRepository
import ru.hse.restaurant.management.system.services.AuthUserDetailsService
import ru.hse.restaurant.management.system.services.UserService

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class AuthConfiguration {
    @Bean
    fun userDetailsService(userService: UserService): UserDetailsService {
        return AuthUserDetailsService(userService)
    }

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(userService: UserService): AuthenticationProvider {
        val authProvider =
            DaoAuthenticationProvider().also {
                it.setUserDetailsService(userDetailsService(userService))
                it.setPasswordEncoder(encoder())
            }
        return authProvider
    }

    @Bean
    fun AuthenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }
}