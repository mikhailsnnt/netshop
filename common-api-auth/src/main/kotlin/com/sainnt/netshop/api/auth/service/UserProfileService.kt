package com.sainnt.netshop.api.auth.service

import com.sainnt.netshop.common.dto.security.LoginDto
import com.sainnt.netshop.common.dto.security.UserDto
import com.sainnt.netshop.common.dto.security.SignUpRequestDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient("COMMON-API-USERPROFILE")
interface UserProfileService {
    @RequestMapping(method = [RequestMethod.POST], value =["/user"])
    fun save(userSignUpRequestDto: SignUpRequestDto): Long

    @RequestMapping(method = [RequestMethod.POST], value =["/user/validate"])
    fun validateCredentials(loginRequestDto: LoginDto): UserDto
}