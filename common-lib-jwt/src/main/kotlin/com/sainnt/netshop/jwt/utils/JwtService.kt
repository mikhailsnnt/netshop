package com.sainnt.netshop.jwt.utils

import com.sainnt.netshop.common.exception.security.ExpiredJwtToken
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.*

class JwtService(private val publicKey: String) {
    fun parseClaims(token:String): JwtTokenData{
        try{
        return Jwts.parser().setSigningKey(getPublicKey(publicKey.toByteArray())).parseClaimsJws(token).let {
            JwtTokenData(it.body.subject, it.body.getOrDefault(ROLES_KEY, emptyList<String>()) as List<String>)
        }
        }catch (e: ExpiredJwtException){
            throw ExpiredJwtToken()
        }
    }

    private fun getPublicKey(bytes: ByteArray): PublicKey {
        return Base64.getDecoder().decode(bytes).let {
            val spec = X509EncodedKeySpec(bytes)
            val factory = KeyFactory.getInstance("RSA")
            factory.generatePublic(spec)
        }
    }

    companion object{
        const val ROLES_KEY = "rol"
    }
}