package com.sainnt.netshop.service

import com.sainnt.netshop.dto.UserDto
import com.sainnt.netshop.entity.RoleEnum
import org.springframework.data.domain.Page

interface UserService {
    fun getUserFromSecurityContext(): UserDto

    fun findById(id: Long): UserDto

    fun findByPhoneOrEmail(phoneOrEmail: String): UserDto

    fun findAll(page: Int = 0, size: Int): Page<UserDto>

    fun updateRoles(userId: Long, roles: List<RoleEnum>): UserDto

    fun deleteById(userId: Long)
}