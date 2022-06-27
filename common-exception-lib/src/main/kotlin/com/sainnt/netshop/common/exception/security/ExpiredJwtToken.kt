package com.sainnt.netshop.common.exception.security

import com.sainnt.netshop.common.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN)
class ExpiredJwtToken: CustomException("Jwt token is expired", HttpStatus.FORBIDDEN)