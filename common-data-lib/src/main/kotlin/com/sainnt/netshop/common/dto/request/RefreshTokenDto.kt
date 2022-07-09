package com.sainnt.netshop.common.dto.request

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class RefreshTokenDto(
    val token: String,
    val expiresAt: Long? = null
)