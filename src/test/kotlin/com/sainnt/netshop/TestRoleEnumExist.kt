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
    fun rolesExist() {
        assertDoesNotThrow { RoleEnum.values().forEach(roleRepository::find)}
    }

}