package com.sainnt.netshop.api.product.config

import com.sainnt.netshop.jwt.spring.JwtAuthenticationFilter
import com.sainnt.netshop.jwt.utils.JwtService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


//TODO Add config server
@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Value("\${netshop.security.pbkey}")
    private val publicKey: String) {
    @Bean
    fun passwordEncryptionProvider(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    @Bean
    fun authenticationFilter():JwtAuthenticationFilter {
        return JwtAuthenticationFilter(JwtService(publicKey))
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .addFilterBefore(authenticationFilter(),UsernamePasswordAuthenticationFilter::class.java)
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET).permitAll()
            .anyRequest().authenticated()
        return http.build()
    }
}