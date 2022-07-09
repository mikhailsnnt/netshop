package com.sainnt.netshop.api.auth.service.impl

import com.sainnt.netshop.api.auth.service.AuthenticationService
import com.sainnt.netshop.api.auth.service.RefreshTokenService
import com.sainnt.netshop.api.auth.service.TokenService
import com.sainnt.netshop.api.auth.service.UserProfileService
import com.sainnt.netshop.common.dto.security.*
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val userProfileService: UserProfileService,
    private val refreshTokenService: RefreshTokenService,
    private val tokenService: TokenService
) : AuthenticationService {
    override fun signup(signUpRequestDto: SignUpRequestDto, roles: List<RoleEnum>): AuthDto {
        return userProfileService.save(signUpRequestDto).let{generateTokenForUser(it, listOf())}
    }

    override fun logIn(loginDto: LoginDto): AuthDto {
        return userProfileService.validateCredentials(loginDto).let{generateTokenForUser(it.id, it.roles?: emptyList())}
    }

    private fun generateTokenForUser(userId: Long, roles: List<String>): AuthDto {
        return tokenService.generateToken(userId, roles).also {
            refreshTokenService.updateRefreshToken(userId, it.refreshToken.token)
        }
    }
}