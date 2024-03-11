package ru.hse.restaurant.management.system.models

data class AuthenticationRequest(
    val phoneNum: String,
    val password: String
)