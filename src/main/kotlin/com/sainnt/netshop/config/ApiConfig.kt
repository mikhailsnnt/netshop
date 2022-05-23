package com.sainnt.netshop.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class ApiConfig(
    @Value("\${api.config.defaultPageSize}")
    val defaultPageSize: Int,
    @Value("\${api.config.maxPageSize}")
    val mapPageSize: Long
)