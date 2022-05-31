package com.sainnt.netshop.common.dto.security

data class JwtAuthenticationTokenDto (
    val token: String
){
    val tokenType="Bearer"
}