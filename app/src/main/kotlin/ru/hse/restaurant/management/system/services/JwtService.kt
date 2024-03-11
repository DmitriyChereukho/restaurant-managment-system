package ru.hse.restaurant.management.system.services

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import ru.hse.restaurant.management.system.config.JwtProperties
import java.util.*

@Service
class JwtService(jwtProperties: JwtProperties) {
    private val secretKey = Keys.hmacShaKeyFor(jwtProperties.key.toByteArray())

    fun generate(
        userDetails: UserDetails, expirationDate: Date, additionalClaims: Map<String, Any> = emptyMap()
    ): String {
        return Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(additionalClaims)
            .and().signWith(secretKey)
            .compact()
    }

    fun extractPhoneNum(token: String): String? {
        return getAllClaims(token).subject
    }

    private fun extractExpiration(token: String): Date {
        return getAllClaims(token).expiration
    }

    fun isExpired(token: String): Boolean {
        return extractExpiration(token).before(Date(System.currentTimeMillis()))
    }

    fun isValid(token: String, userDetails: UserDetails): Boolean {
        val phoneNum = extractPhoneNum(token)
        return phoneNum == userDetails.username && !isExpired(token)
    }

    private fun getAllClaims(token: String): Claims {

        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }
}