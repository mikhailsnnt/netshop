package com.sainnt.netshop.common.dto.security

data class LoginDto(
    val phoneOrEmail: String,
    val password: String
)
