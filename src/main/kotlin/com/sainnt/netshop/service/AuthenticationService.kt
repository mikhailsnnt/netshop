package com.sainnt.netshop.service

import com.sainnt.netshop.dto.JwtAuthenticationTokenDto
import com.sainnt.netshop.dto.LoginDto
import com.sainnt.netshop.dto.SignUpRequestDto
import com.sainnt.netshop.entity.RoleEnum

interface AuthenticationService {
    fun signup(signUpRequestDto: SignUpRequestDto, roles: List<RoleEnum> = listOf()): JwtAuthenticationTokenDto

    fun logIn(loginDto: LoginDto): JwtAuthenticationTokenDto
}