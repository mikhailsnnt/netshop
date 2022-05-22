package com.sainnt.netshop

import com.fasterxml.jackson.databind.ObjectMapper
import com.sainnt.netshop.dto.SignUpRequestDto
import com.sainnt.netshop.entity.RoleEnum
import com.sainnt.netshop.repository.RoleRepository
import com.sainnt.netshop.repository.UserRepository
import com.sainnt.netshop.service.AuthenticationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NetShopApplication(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    private val authenticationService: AuthenticationService,
    private val objectMapper: ObjectMapper
): CommandLineRunner {
    override fun run(vararg args: String?) {
        val adminsRegistrationJsons = args.filterNotNull().filter { it.startsWith("--admin_credentials=\"{") }
        if (adminsRegistrationJsons.isNotEmpty()) {
            adminsRegistrationJsons.map{
                val credentialsJson = it.trim()
                    .substringAfter("--admin_credentials=\"")
                    .substringBeforeLast("\"")
                credentialsJson
            }.map{
                objectMapper.readValue(it, SignUpRequestDto::class.java)
            }.forEach{
                authenticationService.signup(it, listOf(RoleEnum.ADMIN))
                logger.info("Admin ${it.phoneNumber} account created")
            }
        } else if(!userRepository.existsByRolesContaining(roleRepository.find(RoleEnum.ADMIN))) {
            logger.warn("No admins in DB, and no initialization arguments provided")
        }
    }
}
private val logger: Logger = LoggerFactory.getLogger(NetShopApplication::class.java)

fun main(args: Array<String>) {
    runApplication<NetShopApplication>(*args)
}