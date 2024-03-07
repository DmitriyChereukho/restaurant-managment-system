package ru.hse.restaurant.management.system.contollers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.hse.restaurant.management.system.models.AuthenticationRequest
import ru.hse.restaurant.management.system.models.AuthenticationResponse
import ru.hse.restaurant.management.system.services.AuthenticationService

@RestController
@RequestMapping("/auth")
class AuthController(@Autowired private val authenticationService: AuthenticationService) {

        @PostMapping("")
        @ResponseBody
        fun authenticate(@RequestBody authRequest: AuthenticationRequest) : AuthenticationResponse {
            return authenticationService.authenticate(authRequest)
        }

}