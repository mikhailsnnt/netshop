package com.sainnt.netshop.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter(
    private val jwtTokenHelper: JwtTokenHelper,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        getUsernameFromToken(request)?.let(userDetailsService::loadUserByUsername)
            ?.let {
                val token =
                    UsernamePasswordAuthenticationToken(it.username, null, it.authorities)
                token.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = token
            }
        filterChain.doFilter(request, response)
    }

    fun getUsernameFromToken(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")?.let {
            it.substringAfter("Bearer ")
        }?.apply { jwtTokenHelper.validateToken(this) }
            ?.let(jwtTokenHelper::getUsernameFromJwt)
    }


}