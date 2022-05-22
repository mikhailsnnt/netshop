package com.sainnt.netshop

import com.sainnt.netshop.entity.RoleEnum
import com.sainnt.netshop.repository.RoleRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestRoleEnumExist(@Autowired private val roleRepository: RoleRepository) {
    @Test
    fun adminRoleExists() {
        assertDoesNotThrow { roleRepository.find(RoleEnum.ADMIN) }
    }

    @Test
    fun managerRoleExists() {
        assertDoesNotThrow { roleRepository.find(RoleEnum.MANAGER) }
    }

    @Test
    fun userRoleExists() {
        assertDoesNotThrow { roleRepository.find(RoleEnum.USER) }
    }
}