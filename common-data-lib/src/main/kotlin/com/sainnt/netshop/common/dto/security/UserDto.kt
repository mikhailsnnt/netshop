package com.sainnt.netshop.common.dto.security

data class UserDto(
    val id: Long,
    val phoneNumber: String,
    val email: String? = null,
    val name: String,
    val surname: String,
    val patronymic: String? = null,
    val roles: List<String>? = null
)