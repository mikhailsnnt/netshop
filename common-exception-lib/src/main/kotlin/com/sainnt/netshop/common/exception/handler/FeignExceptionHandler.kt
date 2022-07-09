package com.sainnt.netshop.common.exception.handler

import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class FeignExceptionHandler(
    private val exceptionsToRethrow: List<HttpStatus> = listOf(
        HttpStatus.NOT_FOUND,
        HttpStatus.FORBIDDEN,
        HttpStatus.BAD_REQUEST,
        HttpStatus.UNPROCESSABLE_ENTITY
    )
) : ResponseEntityExceptionHandler() {
    @ExceptionHandler(FeignException::class)
    fun handleFeignStatusException(e: FeignException, request: WebRequest): ResponseEntity<String> {
        logger.info("Handling exception: ", e)
        val statusCode = HttpStatus.valueOf(e.status())
        return if (exceptionsToRethrow.contains(statusCode)) {
            val message = e.responseBody().map { String(it.array()) }.orElse("")
            ResponseEntity(message, statusCode)
        }
        else
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}