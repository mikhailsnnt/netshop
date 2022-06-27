package com.sainnt.netshop.common.exception.security

import com.sainnt.netshop.common.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
class BadCredentialsException : CustomException("Bad credentials", HttpStatus.UNPROCESSABLE_ENTITY)