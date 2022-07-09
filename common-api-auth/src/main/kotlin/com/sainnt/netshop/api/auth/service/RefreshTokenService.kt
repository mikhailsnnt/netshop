package com.sainnt.netshop.api.auth.service

import com.sainnt.netshop.common.dto.request.RefreshAccessTokenDto
import com.sainnt.netshop.common.dto.security.AuthDto

interface RefreshTokenService {
    fun refreshAccessToken(refreshAccessTokenDto: RefreshAccessTokenDto): AuthDto

    fun updateRefreshToken(userId: Long, refreshToken: String)
}