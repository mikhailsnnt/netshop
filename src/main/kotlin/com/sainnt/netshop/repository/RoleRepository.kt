package com.sainnt.netshop.repository

import com.sainnt.netshop.entity.Role
import com.sainnt.netshop.entity.Roles
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RoleRepository : JpaRepository<Role, Long> {
    @Query("select r from Role r where r.name = :#{#roleEnum.toString()}")
    fun find(roleEnum: Roles): Role
}