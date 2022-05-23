package com.sainnt.netshop.repository

import com.sainnt.netshop.entity.Role
import com.sainnt.netshop.entity.User
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository : PagingAndSortingRepository<User, Long>{

    fun findByPhoneNumberOrEmail(phoneNumber: String?, email: String?): User?

    fun existsByPhoneNumber(phoneNumber: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun existsByRolesContaining(role: Role): Boolean
}