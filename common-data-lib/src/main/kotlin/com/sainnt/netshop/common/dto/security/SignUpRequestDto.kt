package com.sainnt.netshop.common.dto.security

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


data class SignUpRequestDto(
        @NotBlank
        val name: String,
        @NotBlank
        val surname: String,
        @Size(min = 1, message = "Patronymic cannot be blank")
        val patronymic: String?,
        val phoneNumber: String,
        @Email
        val email: String?,
        val password: String,
)
