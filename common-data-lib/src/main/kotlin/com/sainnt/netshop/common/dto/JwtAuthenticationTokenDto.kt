package com.sainnt.netshop.common.dto

data class JwtAuthenticationTokenDto (
    val token: String
){
    val tokenType="Bearer"
}