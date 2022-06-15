package com.sainnt.netshop.api.auth.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class SecurityConfig (
    @Value("\${jwt.secret}")
    val privateKey: String,
    @Value("\${jwt.tokenExpiration}")
    val tokenExpiration: Long
    )