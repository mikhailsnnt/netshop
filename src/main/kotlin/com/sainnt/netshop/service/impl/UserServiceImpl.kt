package com.sainnt.netshop.service.impl

import com.sainnt.netshop.dto.UserDto
import com.sainnt.netshop.entity.Role
import com.sainnt.netshop.entity.RoleEnum
import com.sainnt.netshop.entity.User
import com.sainnt.netshop.exception.NotFoundException
import com.sainnt.netshop.repository.RoleRepository
import com.sainnt.netshop.repository.UserRepository
import com.sainnt.netshop.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository
) : UserService {
    override fun getUserFromSecurityContext(): UserDto {
        val userPhone = SecurityContextHolder.getContext()
            .authentication
            .principal
        return findByPhoneOrEmail(userPhone as String)
    }

    override fun findById(id: Long): UserDto {
        return retrieve(id).let(this::mapToDto)
    }

    override fun findByPhoneOrEmail(phoneOrEmail: String): UserDto {
        return userRepository.findByPhoneNumberOrEmail(phoneOrEmail, phoneOrEmail)
            ?.let(this::mapToDto) ?: throw NotFoundException("User with phone/email $phoneOrEmail not found")
    }

    override fun findAll(page: Int, size: Int): Page<UserDto> {
        return userRepository.findAll(PageRequest.of(page, size)).map(this::mapToDto)
    }

    override fun updateRoles(userId: Long, roles: List<RoleEnum>): UserDto {
        return retrieve(userId).let {
            it.roles = roles.map(roleRepository::find)
            userRepository.save(it)
            return mapToDto(it)
        }
    }

    override fun deleteById(id: Long) {
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