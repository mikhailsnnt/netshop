package com.sainnt.netshop.api.auth.controller

import com.sainnt.netshop.api.auth.service.AuthenticationService
import com.sainnt.netshop.api.auth.service.RefreshTokenService
import com.sainnt.netshop.common.dto.request.RefreshAccessTokenDto
import com.sainnt.netshop.common.dto.security.AuthDto
import com.sainnt.netshop.common.dto.security.LoginDto
import com.sainnt.netshop.common.dto.security.SignUpRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationService: AuthenticationService,
    private val refreshTokenService: RefreshTokenService,
    ) {
    @PostMapping("/signup")
    fun signup(@RequestBody signupRequestDto: SignUpRequestDto): ResponseEntity<AuthDto> {
        return ResponseEntity.ok(
            authenticationService.signup(signupRequestDto)
        )
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDto: LoginDto): ResponseEntity<AuthDto> {
        return ResponseEntity.ok(
            authenticationService.logIn(loginRequestDto)
        )
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody refreshAccessTokenDto: RefreshAccessTokenDto): ResponseEntity<AuthDto>{
        return ResponseEntity.ok(
            refreshTokenService.refreshAccessToken(refreshAccessTokenDto)
        )
    }
}