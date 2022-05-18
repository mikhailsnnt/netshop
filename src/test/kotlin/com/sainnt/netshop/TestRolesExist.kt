package com.sainnt.netshop

import com.sainnt.netshop.entity.Roles
import com.sainnt.netshop.repository.RoleRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestRolesExist(@Autowired private val roleRepository: RoleRepository) {
    @Test
    fun adminRoleExists() {
        assertDoesNotThrow { roleRepository.find(Roles.ADMIN) }
    }

    @Test
    fun managerRoleExists() {
        assertDoesNotThrow { roleRepository.find(Roles.MANAGER) }
    }

    @Test
    fun userRoleExists() {
        assertDoesNotThrow { roleRepository.find(Roles.USER) }
    }
}