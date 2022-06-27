package com.sainnt.netshop.common.dto.request

data class LoginDto(
    val phoneOrEmail: String,
    val password: String
)
