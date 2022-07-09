package com.sainnt.netshop.api.auth.service.impl

import com.sainnt.netshop.api.auth.redis.RefreshToken
import com.sainnt.netshop.api.auth.redis.RefreshTokenRepository
import com.sainnt.netshop.api.auth.service.RefreshTokenService
import com.sainnt.netshop.api.auth.service.TokenService
import com.sainnt.netshop.api.auth.service.UserProfileService
import com.sainnt.netshop.common.dto.request.RefreshAccessTokenDto
import com.sainnt.netshop.common.dto.security.AuthDto
import com.sainnt.netshop.common.exception.security.ForbiddenException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RefreshTokenServiceImpl(
    private val tokenService: TokenService,
    private val userProfileService: UserProfileService,
    private val refreshTokenRepository: RefreshTokenRepository,
) : RefreshTokenService {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun refreshAccessToken(refreshAccessTokenDto: RefreshAccessTokenDto): AuthDto {
        tokenService.parseClaims(refreshAccessTokenDto.refreshToken).subject.toLong()
            .let { userId ->
                val tokenDbSearch = refreshTokenRepository.findById(userId)
                if (tokenDbSearch.isEmpty || tokenDbSearch.get().token != refreshAccessTokenDto.refreshToken) {
                    logger.info("Token for user $userId not equals ${refreshAccessTokenDto.refreshToken}")
                    throw ForbiddenException("Refresh token is not verified")
                }
                return userProfileService
                    .byId(userId, serviceToken = tokenService.getServiceAccessToken().let { it.tokenType + " " + it.token })
                    .let {
                        tokenService.generateToken(userId, it.roles ?: emptyList())
                    }
                    .also {
                        updateRefreshToken(userId, it.refreshToken.token)
                    }
            }
    }

    override fun updateRefreshToken(userId: Long, refreshToken: String) {
        refreshTokenRepository.save(RefreshToken(userId, refreshToken))
    }
}