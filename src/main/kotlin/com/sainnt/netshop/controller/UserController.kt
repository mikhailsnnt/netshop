package com.sainnt.netshop.controller

import com.sainnt.netshop.dto.UserDto
import com.sainnt.netshop.entity.RoleEnum
import com.sainnt.netshop.service.UserService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min


@Controller
@RequestMapping("/v1/user")
class UserController(private val userService: UserService) {

    companion object {
        const val DEFAULT_PAGE_SIZE = 30
        const val MAX_PAGE_SIZE = 60L
    }

    @GetMapping
    fun getAuthenticatedUser(): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.getUserFromSecurityContext())
    }

    @PreAuthorize("hasRole('T(com.sainnt.netshop.entity.RoleEnum).ADMIN')")
    @GetMapping("/all")
    fun getAllUsers(
        @Min(1, message = "Page number cannot be less then 1") @RequestParam(defaultValue = "1") page: Int,
        @Max(MAX_PAGE_SIZE, message = "Page size cannot be greater then $MAX_PAGE_SIZE")
        @RequestParam(required = false) pageSize: Int?
    ): ResponseEntity<Page<UserDto>> {
        return ResponseEntity.ok(userService.findAll(page - 1, pageSize ?: DEFAULT_PAGE_SIZE))
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{userId}")
    fun getUserById(@PathVariable userId: Long): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.findById(userId))
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userId}")
    fun updateUserRoles(@PathVariable userId: Long, @RequestBody roles: List<RoleEnum>): ResponseEntity<UserDto>{
        return ResponseEntity.ok(userService.updateRoles(userId,roles))
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    fun deleteUserById(@PathVariable userId: Long): ResponseEntity<String> {
        userService.deleteById(userId)
        return ResponseEntity.ok("User $userId deleted")
    }
}