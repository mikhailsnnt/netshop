package com.sainnt.netshop.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(override val message: String? = null) : CustomException(message, HttpStatus.NOT_FOUND)