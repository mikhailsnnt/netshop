package com.sainnt.netshop.controller

import com.sainnt.netshop.dto.JwtAuthenticationTokenDto
import com.sainnt.netshop.dto.LoginDto
import com.sainnt.netshop.dto.SignUpRequestDto
import com.sainnt.netshop.service.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
class AuthController(private val authenticationService: AuthenticationService) {
    @PostMapping("/signup")
    fun signup(@RequestBody signupRequestDto: SignUpRequestDto): ResponseEntity<JwtAuthenticationTokenDto> {
        return ResponseEntity.ok(
            authenticationService.signup(signupRequestDto)
        )
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDto: LoginDto): ResponseEntity<JwtAuthenticationTokenDto> {
        return ResponseEntity.ok(
            authenticationService.logIn(loginRequestDto)
        )
    }
}