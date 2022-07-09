package com.sainnt.netshop.common.dto.security

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class JwtAccessTokenDto (
    val token: String,
    val expiresAt: Long? = null
){
    val tokenType="Bearer"
}