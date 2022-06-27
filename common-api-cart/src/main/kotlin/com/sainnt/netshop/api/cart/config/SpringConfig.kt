package com.sainnt.netshop.api.cart.config

import com.sainnt.netshop.common.exception.handler.FeignExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig {
    @Bean
    fun feignExceptionHandler(): FeignExceptionHandler{
        return FeignExceptionHandler()
    }
}