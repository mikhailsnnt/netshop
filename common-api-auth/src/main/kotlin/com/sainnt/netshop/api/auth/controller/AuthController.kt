package com.sainnt.netshop.api.auth.controller

import com.sainnt.netshop.api.auth.service.AuthenticationService
import com.sainnt.netshop.common.dto.security.JwtAuthenticationTokenDto
import com.sainnt.netshop.common.dto.security.LoginDto
import com.sainnt.netshop.common.dto.security.SignUpRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
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