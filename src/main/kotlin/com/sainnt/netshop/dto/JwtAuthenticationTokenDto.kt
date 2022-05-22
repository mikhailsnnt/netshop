package com.sainnt.netshop.dto

data class JwtAuthenticationTokenDto (
    val token: String
){
    val tokenType="Bearer"
}