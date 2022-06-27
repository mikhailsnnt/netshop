package com.sainnt.netshop.api.auth.service

import com.sainnt.netshop.common.dto.security.JwtAuthenticationTokenDto
import com.sainnt.netshop.common.dto.security.RoleEnum
import com.sainnt.netshop.common.dto.security.SignUpRequestDto
import com.sainnt.netshop.common.dto.security.LoginDto

interface AuthenticationService {
    fun signup(signUpRequestDto: SignUpRequestDto, roles: List<RoleEnum> = listOf()): JwtAuthenticationTokenDto

    fun logIn(loginDto: LoginDto): JwtAuthenticationTokenDto
}