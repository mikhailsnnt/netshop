package com.sainnt.netshop.api.userprofile.controller

import com.sainnt.netshop.api.userprofile.service.UserService
import com.sainnt.netshop.api.userprofile.config.ApiConfig
import com.sainnt.netshop.common.dto.security.LoginDto
import com.sainnt.netshop.common.dto.security.RoleEnum
import com.sainnt.netshop.common.dto.security.SignUpRequestDto
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import com.sainnt.netshop.common.dto.security.UserDto
import com.sainnt.netshop.common.exception.NetShopApiException
import javax.validation.constraints.Min


@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
    private val apiConfig: ApiConfig
) {

    @PostMapping
    fun createUser(@RequestBody userSignUpRequestDto: SignUpRequestDto): ResponseEntity<Long>{
        return ResponseEntity.ok(userService.createUser(userSignUpRequestDto))
    }

    @PostMapping("/validate")
    fun validateCredentials(@RequestBody loginDto: LoginDto): ResponseEntity<UserDto>{
        return ResponseEntity.ok(userService.validateCredentials(loginDto))
    }


    @GetMapping
    fun getAuthenticatedUser(): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.getUserFromSecurityContext())
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    fun getAllUsers(
        @Min(1, message = "Page number cannot be less then 1") @RequestParam(defaultValue = "1") page: Int,
        @Min(1, message = "Page size must be greater then 1") @RequestParam(
            required = false, defaultValue = "\${api.config.defaultPageSize}"
        ) pageSize: Int
    ): ResponseEntity<Page<UserDto>> {
        if (pageSize > apiConfig.mapPageSize) throw NetShopApiException("Page size cannot be greater then ${apiConfig.mapPageSize}")
        return ResponseEntity.ok(userService.findAll(page - 1, pageSize))
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Long): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.findById(userId))
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userId}/role")
    fun updateUserRoles(@PathVariable userId: Long, @RequestBody roles: List<RoleEnum>): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.updateRoles(userId, roles))
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    fun deleteUserById(@PathVariable userId: Long): ResponseEntity<String> {
        userService.deleteById(userId)
        return ResponseEntity.ok("User $userId deleted")
    }
}