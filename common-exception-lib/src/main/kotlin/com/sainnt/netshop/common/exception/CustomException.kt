package com.sainnt.netshop.common.exception

import org.springframework.http.HttpStatus

abstract class CustomException(message: String?,val statusCode: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR) : RuntimeException(message) {

}