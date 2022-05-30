package com.sainnt.netshop.api.userprofile.service

import com.sainnt.netshop.api.userprofile.entity.Role
import com.sainnt.netshop.api.userprofile.entity.User
import com.sainnt.netshop.api.userprofile.repository.RoleRepository
import com.sainnt.netshop.api.userprofile.repository.UserRepository
import com.sainnt.netshop.common.dto.RoleEnum
import com.sainnt.netshop.common.dto.UserDto
import com.sainnt.netshop.common.exception.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository
) {
    fun getUserFromSecurityContext(): UserDto {
        val userPhone = SecurityContextHolder.getContext()
            .authentication
            .principal
        return findByPhoneOrEmail(userPhone as String)
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

}