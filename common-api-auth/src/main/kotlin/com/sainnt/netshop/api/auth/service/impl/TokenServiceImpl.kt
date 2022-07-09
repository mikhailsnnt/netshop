package com.sainnt.netshop.api.auth.service.impl

import com.sainnt.netshop.api.auth.config.SecurityConfig
import com.sainnt.netshop.api.auth.service.TokenService
import com.sainnt.netshop.common.dto.security.AuthDto
import com.sainnt.netshop.common.dto.security.JwtAccessTokenDto
import com.sainnt.netshop.common.dto.security.JwtRefreshTokenDto
import com.sainnt.netshop.jwt.utils.JwtService
import com.sainnt.netshop.jwt.utils.JwtTokenData
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*

@Service
class TokenServiceImpl(
    @Value("\${jwt.secret}")
    privateKey: String,
    private val securityConfig: SecurityConfig,
    private val jwtService: JwtService
) : TokenService {
    private val signKey = getPrivateKey(privateKey.toByteArray())

    private lateinit var serviceToken: JwtAccessTokenDto
    private var serviceTokenExpiration: Long = 0

    override fun generateToken(userId: Long, roles: List<String>): AuthDto {
        val date = Date()

        val accessToken = Jwts.builder()
            .setClaims(mutableMapOf<String, Any>(ROLES_KEY to roles))
            .setSubject(userId.toString())
            .setIssuedAt(date)
            .setExpiration(Date(date.time + securityConfig.tokenExpiration))
            .signWith(SignatureAlgorithm.RS256, signKey)
            .compact()
            .let{JwtAccessTokenDto(it, securityConfig.tokenExpiration/1000)}

        val refreshToken = Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(date)
            .setExpiration(Date(date.time + securityConfig.refreshTokenExpiration))
            .signWith(SignatureAlgorithm.RS256, signKey)
            .compact()
            .let { JwtRefreshTokenDto(it, securityConfig.refreshTokenExpiration/1000) }
        return AuthDto(accessToken, refreshToken)
    }

    override fun parseClaims(token: String): JwtTokenData {
        return jwtService.parseClaims(token)
    }

    @Synchronized
    override fun getServiceAccessToken(): JwtAccessTokenDto {
        //TODO Create service role ?
        if(serviceTokenExpiration < System.currentTimeMillis()) {
            serviceToken =  generateToken(0, listOf("ADMIN" , "MANAGER")).accessToken
            // 1s simple precaution
            serviceTokenExpiration = System.currentTimeMillis() - 1000
        }
        return serviceToken
    }


    private final fun getPrivateKey(bytes: ByteArray): PrivateKey {
        return Base64.getDecoder().decode(bytes).let {
            val spec = PKCS8EncodedKeySpec(it)
            val factory = KeyFactory.getInstance("RSA")
            factory.generatePrivate(spec)
        }
    }

    companion object {
        const val ROLES_KEY = "rol"
    }
}