package com.sainnt.netshop.jwt.utils

import com.sainnt.netshop.common.exception.security.ExpiredJwtToken
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts

class JwtService(private val publicKey: String) {
    fun parseClaims(token:String): JwtTokenData{
        try{
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).let {
            JwtTokenData(it.body.subject, it.body.getOrDefault(ROLES_KEY, emptyList<String>()) as List<String>)
        }
        }catch (e: ExpiredJwtException){
            throw ExpiredJwtToken()
        }
    }

    companion object{
        const val ROLES_KEY = "rol"
    }
}