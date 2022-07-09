package com.sainnt.netshop.common.dto.security

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class JwtRefreshTokenDto(
    val token: String,
    val expiresAt: Long? = null
)