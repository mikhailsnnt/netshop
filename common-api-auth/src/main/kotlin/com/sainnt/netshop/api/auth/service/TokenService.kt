package com.sainnt.netshop.api.auth.service

import com.sainnt.netshop.common.dto.security.AuthDto
import com.sainnt.netshop.common.dto.security.JwtAccessTokenDto
import com.sainnt.netshop.jwt.utils.JwtTokenData

interface TokenService{
    fun generateToken(userId: Long, roles: List<String>): AuthDto

    fun parseClaims(token: String): JwtTokenData
    //TODO Move to separate location
    fun getServiceAccessToken(): JwtAccessTokenDto
}