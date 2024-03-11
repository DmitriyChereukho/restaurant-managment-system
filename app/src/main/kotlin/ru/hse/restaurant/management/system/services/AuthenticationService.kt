package ru.hse.restaurant.management.system.services

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import ru.hse.restaurant.management.system.config.JwtProperties
import ru.hse.restaurant.management.system.models.AuthenticationRequest
import ru.hse.restaurant.management.system.models.AuthenticationResponse
import java.util.*

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: AuthUserDetailsService,
    private val jwtService: JwtService,
    private val jwtProperties: JwtProperties
) {
    fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.phoneNum,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.phoneNum)
        val accessToken = jwtService.generate(
            userDetails = user,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
        )
        return AuthenticationResponse(accessToken)
    }

}