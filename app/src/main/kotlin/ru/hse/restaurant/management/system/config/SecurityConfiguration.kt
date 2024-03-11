package ru.hse.restaurant.management.system.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(private val authenticationProvider: AuthenticationProvider) {
    @Bean
    fun securityFilterChain(http: HttpSecurity, jwtFilter: JwtFilter): DefaultSecurityFilterChain {
        return http.csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/auth", "/auth/refresh", "/error")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/users")
                    .permitAll()
                    .requestMatchers("/orders")
                    .permitAll()
                    .requestMatchers("/orders**")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/dishes")
                    .permitAll()
                    .requestMatchers("/users**", "/users/admin", "/dishes**", "/stat**")
                    .hasRole("ADMIN")
                    .anyRequest()
                    .fullyAuthenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}