package com.sainnt.netshop.api.userprofile.repository

import com.sainnt.netshop.api.userprofile.entity.Role
import com.sainnt.netshop.api.userprofile.entity.User
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository : PagingAndSortingRepository<User, Long>{

    fun findByPhoneNumberOrEmail(phoneNumber: String?, email: String?): User?

    fun existsByPhoneNumber(phoneNumber: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun existsByRolesContaining(role: Role): Boolean
}