package com.sainnt.netshop.common.dto.security

data class AuthDto(
    val accessToken: JwtAccessTokenDto,
    val refreshToken: JwtRefreshTokenDto,
)