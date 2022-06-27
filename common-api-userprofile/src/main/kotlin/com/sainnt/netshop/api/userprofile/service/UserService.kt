package com.sainnt.netshop.api.userprofile.service

import com.sainnt.netshop.api.userprofile.entity.Credentials
import com.sainnt.netshop.api.userprofile.entity.Role
import com.sainnt.netshop.api.userprofile.entity.User
import com.sainnt.netshop.api.userprofile.repository.CredentialsRepository
import com.sainnt.netshop.api.userprofile.repository.RoleRepository
import com.sainnt.netshop.api.userprofile.repository.UserRepository
import com.sainnt.netshop.common.dto.security.LoginDto
import com.sainnt.netshop.common.dto.security.RoleEnum
import com.sainnt.netshop.common.dto.security.SignUpRequestDto
import com.sainnt.netshop.common.dto.security.UserDto
import com.sainnt.netshop.common.exception.NetShopApiException
import com.sainnt.netshop.common.exception.NotFoundException
import com.sainnt.netshop.common.exception.security.BadCredentialsException
import com.sainnt.netshop.jwt.utils.JwtTokenData
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val credentialsRepository: CredentialsRepository,
    private val passwordEncoder: PasswordEncoder,
    private val kafkaTemplate: KafkaTemplate<String,String>,
    @Value("\${netshop.kafka.topics.userprofile}") //TODO Configuration server
    private val topic:String,
) {
    fun getUserFromSecurityContext(): UserDto {
        val tokenData = SecurityContextHolder.getContext()
            .authentication
            .principal as JwtTokenData
        return findById(tokenData.subject.toLong())
    }

    fun findById(id: Long): UserDto {
        return retrieve(id).let(this::mapToDto)
    }

    fun findByPhoneOrEmail(phoneOrEmail: String): UserDto {
        return userRepository.findByPhoneNumberOrEmail(phoneOrEmail, phoneOrEmail)
            ?.let(this::mapToDto) ?: throw NotFoundException("User with phone/email $phoneOrEmail not found")
    }

    fun findAll(page: Int, size: Int): Page<UserDto> {
        return userRepository.findAll(PageRequest.of(page, size)).map(this::mapToDto)
    }

    fun updateRoles(userId: Long, roles: List<RoleEnum>): UserDto {
        return retrieve(userId).let {
            it.roles = roles.map(roleRepository::find)
            userRepository.save(it)
            mapToDto(it)
        }
    }

    fun deleteById(id: Long) {
        retrieve(id).let(userRepository::delete)
    }

    private fun retrieve(id: Long): User {
        return userRepository.findById(id).orElseThrow { NotFoundException("User with id $id not found") }
    }

    private fun mapToDto(user: User): UserDto {
        return UserDto(
            id = user.id!!,
            phoneNumber = user.phoneNumber,
            email = user.email,
            name = user.name,
            surname = user.surname,
            patronymic = user.patronymic,
            roles = user.roles.map(Role::name)
        )
    }

    @Transactional
    fun createUser(signUpRequestDto: SignUpRequestDto): Long {
        if (userRepository.existsByPhoneNumber(signUpRequestDto.phoneNumber))
            throw NetShopApiException("Phone number is already used")
        if (signUpRequestDto.email?.let(userRepository::existsByEmail) == true)
            throw NetShopApiException("Email is already used")
        val user = User()
        user.name = signUpRequestDto.name
        user.surname = signUpRequestDto.surname
        user.patronymic = signUpRequestDto.patronymic
        user.email = signUpRequestDto.email
        user.phoneNumber = signUpRequestDto.phoneNumber
        user.credentials = credentialsRepository.save(Credentials(passwordEncoder.encode(signUpRequestDto.password)))
        val newUserId = userRepository.save(user).id!!
        kafkaTemplate.send(topic,"created",newUserId.toString())
        return newUserId
    }

    fun validateCredentials(loginDto: LoginDto): UserDto {
        val user = userRepository.findByPhoneNumberOrEmail(loginDto.phoneOrEmail,loginDto.phoneOrEmail)
            ?: throw BadCredentialsException()
        if(!passwordEncoder.matches(loginDto.password, user.credentials.credentials))
            throw BadCredentialsException()
        return mapToDto(user)
    }

}