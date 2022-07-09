package com.sainnt.netshop.api.auth.config

import com.sainnt.netshop.jwt.utils.JwtService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SecurityConfig(
    @Value("\${jwt.tokenExpiration}")
    val tokenExpiration: Long,
    @Value("\${jwt.refreshTokenExpiration}")
    val refreshTokenExpiration: Long,

){
    @Bean
    fun jwtService(@Value("\${netshop.security.pbkey}") publicKey: String): JwtService {
        return JwtService(publicKey)
    }
}