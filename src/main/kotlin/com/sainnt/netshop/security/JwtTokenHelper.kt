package com.sainnt.netshop.security

import com.sainnt.netshop.exception.NetShopApiException
import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenHelper(@Value("\${app.security.jwt.secret}")
                     private val secret: String,
                     @Value("\${app.security.jwt.expiration}")
                     private val expiresAtMilliseconds: Long
) {
    fun createToken(authentication: Authentication): String{
        val date = Date()
        return Jwts.builder()
            .setSubject(authentication.name)
            .setIssuedAt(date)
            .setExpiration(Date(date.time + expiresAtMilliseconds))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun getUsernameFromJwt(token: String): String {
        val claims: Claims = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
        return claims.subject
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (exception: ExpiredJwtException) {
            throw NetShopApiException("Expired JWT token")
        } catch (exception: UnsupportedJwtException) {
            throw NetShopApiException("Unsupported JWT token")
        } catch (exception: MalformedJwtException) {
            throw NetShopApiException("Invalid JWT token")
        } catch (exception: SignatureException) {
            throw NetShopApiException("Invalid JWT signature")
        } catch (exception: IllegalArgumentException) {
            throw NetShopApiException("Invalid JWT token argument")
        }
    }
}