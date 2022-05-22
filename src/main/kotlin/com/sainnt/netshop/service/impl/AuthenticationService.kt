package com.sainnt.netshop.service.impl

import com.sainnt.netshop.dto.JwtAuthenticationTokenDto
import com.sainnt.netshop.dto.LoginDto
import com.sainnt.netshop.dto.SignUpRequestDto
import com.sainnt.netshop.entity.Cart
import com.sainnt.netshop.entity.Credentials
import com.sainnt.netshop.entity.RoleEnum
import com.sainnt.netshop.entity.User
import com.sainnt.netshop.exception.NetShopApiException
import com.sainnt.netshop.repository.CartRepository
import com.sainnt.netshop.repository.CredentialsRepository
import com.sainnt.netshop.repository.RoleRepository
import com.sainnt.netshop.repository.UserRepository
import com.sainnt.netshop.security.JwtTokenHelper
import com.sainnt.netshop.service.AuthenticationService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
    private val credentialsRepository: CredentialsRepository,
    private val roleRepository: RoleRepository,
    private val cartRepository: CartRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val tokenHelper: JwtTokenHelper
) : AuthenticationService {

    @Transactional
    override fun signup(signUpRequestDto: SignUpRequestDto, roles: List<RoleEnum>): JwtAuthenticationTokenDto {
        if (userRepository.existsByPhoneNumber(signUpRequestDto.phoneNumber))
            throw NetShopApiException("Phone number is already used")
        if (signUpRequestDto.email?.let(userRepository::existsByEmail) == true)
            throw NetShopApiException("Email is already used")
        var user = mapToEntity(signUpRequestDto)
        user.credentials = credentialsRepository.save(Credentials(passwordEncoder.encode(signUpRequestDto.password)))
        user.cart = cartRepository.save(Cart())
        user.roles = roles.map(roleRepository::find).toList()
        userRepository.save(user)
        return generateToken(signUpRequestDto.phoneNumber, signUpRequestDto.password)
    }

    @Transactional
    override fun logIn(loginDto: LoginDto): JwtAuthenticationTokenDto {
        return generateToken(loginDto.phoneOrEmail, loginDto.password)
    }

    private fun generateToken(username: String, password: String): JwtAuthenticationTokenDto {
        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
            .apply {
                SecurityContextHolder.getContext().authentication = this
            }
            .let(tokenHelper::createToken)
            .let { JwtAuthenticationTokenDto(token = it) }
    }

    private fun mapToEntity(dto: SignUpRequestDto): User {
        var u = User()
        u.name = dto.name
        u.surname = dto.surname
        u.patronymic = dto.patronymic
        u.email = dto.email
        u.phoneNumber = dto.phoneNumber
        return u
    }

}