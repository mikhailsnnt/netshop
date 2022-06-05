package com.sainnt.netshop.jwt.spring

import com.sainnt.netshop.jwt.utils.JwtService
import com.sainnt.netshop.jwt.utils.JwtTokenData
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val jwtTokenHelper: JwtService,
) : OncePerRequestFilter()
{
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        parseRequestTokenData(request)?.let {
                val token =
                    UsernamePasswordAuthenticationToken(it, null, it.roles.map(::SimpleGrantedAuthority))
                token.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = token
            }
        filterChain.doFilter(request, response)
    }

    private fun parseRequestTokenData(request: HttpServletRequest): JwtTokenData? {
        return request.getHeader("Authorization")?.let {
            it.substringAfter("Bearer ")
        }?.let(jwtTokenHelper::parseClaims)
    }
}