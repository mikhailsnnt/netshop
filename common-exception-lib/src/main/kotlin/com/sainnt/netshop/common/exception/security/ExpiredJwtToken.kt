package com.sainnt.netshop.common.exception.security

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN)
class ExpiredJwtToken: RuntimeException()