package com.sainnt.netshop.api.auth.service.impl

import com.sainnt.netshop.api.auth.config.SecurityConfig
import com.sainnt.netshop.api.auth.service.AuthenticationService
import com.sainnt.netshop.api.auth.service.UserProfileService
import com.sainnt.netshop.common.dto.security.*
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*

@Service
class AuthenticationServiceImpl(
    @Value("\${jwt.secret}")
    privateKey: String,
    private val userProfileService: UserProfileService,
    private val securityConfig: SecurityConfig,
) : AuthenticationService {

    private val signKey = getPrivateKey(privateKey.toByteArray())

    override fun signup(signUpRequestDto: SignUpRequestDto, roles: List<RoleEnum>): JwtAuthenticationTokenDto {
        return generateToken(userProfileService.save(signUpRequestDto), listOf())
    }

    override fun logIn(loginDto: LoginDto): JwtAuthenticationTokenDto {
        return userProfileService.validateCredentials(loginDto).let{generateToken(it.id,it.roles?: listOf())}
    }

    private fun generateToken(userId: Long, roles:List<String>): JwtAuthenticationTokenDto {
        val date = Date()
        return Jwts.builder()
                    .setClaims(mutableMapOf<String,Any>(ROLES_KEY to roles))
                    .setSubject(userId.toString())
                    .setIssuedAt(date)
                    .setExpiration(Date(date.time + securityConfig.tokenExpiration))
                    .signWith(SignatureAlgorithm.RS256, signKey)
                    .compact()
            .let(::JwtAuthenticationTokenDto)
    }


    private final fun getPrivateKey(bytes: ByteArray): PrivateKey {
        return Base64.getDecoder().decode(bytes).let {
            val spec = PKCS8EncodedKeySpec(it)
            val factory = KeyFactory.getInstance("RSA")
            factory.generatePrivate(spec)
        }
    }

    companion object{
        const val ROLES_KEY = "rol"
    }
}