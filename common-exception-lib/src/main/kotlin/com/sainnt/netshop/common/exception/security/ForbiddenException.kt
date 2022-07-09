package com.sainnt.netshop.common.exception.security

import com.sainnt.netshop.common.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN)
class ForbiddenException(message: String = "Access forbidden") : CustomException(message, HttpStatus.FORBIDDEN)