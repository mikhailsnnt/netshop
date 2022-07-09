package com.sainnt.netshop.api.userprofile

import com.sainnt.netshop.api.userprofile.entity.Role
import com.sainnt.netshop.api.userprofile.repository.RoleRepository
import com.sainnt.netshop.api.userprofile.repository.UserRepository
import com.sainnt.netshop.api.userprofile.service.UserService
import com.sainnt.netshop.common.dto.security.RoleEnum
import com.sainnt.netshop.common.dto.security.SignUpRequestDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
@EnableEurekaClient
class CommonApiUserprofileApplication(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    private val userService: UserService,
) : CommandLineRunner {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    override fun run(vararg args: String?) {
        RoleEnum.values()
            .filter { !roleRepository.existsByName(it.name) }
            .forEach { roleRepository.save(Role(name = it.name)) }

        if (!userRepository.existsByRolesContaining(roleRepository.find(RoleEnum.ADMIN))) {
            val newUserId = userService.createUser(
                SignUpRequestDto(
                    name = "Admin",
                    surname = "Admin",
                    phoneNumber = "+79319979999",
                    password = "admin",
                    email = null,
                    patronymic = null
                )
            )
            logger.warn("Default +79319999999 / admin account created. Don't forget to change credentials!")
            userService.updateRoles(newUserId, listOf(RoleEnum.ADMIN))
            logger.info("ADMIN role to $newUserId granted")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CommonApiUserprofileApplication>(*args)
}
