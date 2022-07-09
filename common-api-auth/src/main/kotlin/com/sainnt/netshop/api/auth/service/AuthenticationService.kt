package com.sainnt.netshop.api.auth.service

import com.sainnt.netshop.common.dto.security.AuthDto
import com.sainnt.netshop.common.dto.security.LoginDto
import com.sainnt.netshop.common.dto.security.RoleEnum
import com.sainnt.netshop.common.dto.security.SignUpRequestDto

interface AuthenticationService {
    fun signup(signUpRequestDto: SignUpRequestDto, roles: List<RoleEnum> = listOf()): AuthDto

    fun logIn(loginDto: LoginDto): AuthDto
}