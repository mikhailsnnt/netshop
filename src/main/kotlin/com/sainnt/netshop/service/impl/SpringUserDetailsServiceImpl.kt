package com.sainnt.netshop.service.impl

import com.sainnt.netshop.entity.Role
import com.sainnt.netshop.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class SpringUserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(phoneOrEmail: String): UserDetails? {
        return userRepository.findByPhoneNumberOrEmail(phoneOrEmail, phoneOrEmail)?.let {
            User(it.phoneNumber,it.credentials.credentials, it.roles.map(this::mapToAuthority))
        } ?: throw UsernameNotFoundException(phoneOrEmail)
    }

    private fun mapToAuthority(role: Role): GrantedAuthority{
        return SimpleGrantedAuthority(role.name)
    }
}