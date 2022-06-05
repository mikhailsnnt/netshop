package com.sainnt.netshop.api.auth.service.impl

import com.sainnt.netshop.api.auth.config.SecurityConfig
import com.sainnt.netshop.api.auth.service.AuthenticationService
import com.sainnt.netshop.api.auth.service.UserProfileService
import com.sainnt.netshop.common.dto.security.*
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationServiceImpl(
    private val userProfileService: UserProfileService,
    private val securityConfig: SecurityConfig,
) : AuthenticationService {

    override fun signup(signUpRequestDto: SignUpRequestDto, roles: List<RoleEnum>): JwtAuthenticationTokenDto {
        return generateToken(userProfileService.save(signUpRequestDto), listOf())
    }

    override fun logIn(loginDto: LoginDto): JwtAuthenticationTokenDto {
        return userProfileService.validateCredentials(loginDto).let{generateToken(it.id,it.roles?: listOf())}
    }

    private fun generateToken(userId: Long, roles:List<String>): JwtAuthenticationTokenDto {
        val date = Date()
        return Jwts.builder()
                    .setSubject(userId.toString())
                    .setClaims(mutableMapOf<String,Any>(ROLES_KEY to roles))
                    .setIssuedAt(date)
                    .setExpiration(Date(date.time + securityConfig.tokenExpiration))
                    .signWith(SignatureAlgorithm.HS256, securityConfig.privateKey)
                    .compact()
            .let(::JwtAuthenticationTokenDto)
    }

    companion object{
        const val ROLES_KEY = "rol"
    }
}