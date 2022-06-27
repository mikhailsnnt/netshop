package com.sainnt.netshop.jwt.utils

data class JwtTokenData (
    val subject: String,
    val roles: List<String>
)