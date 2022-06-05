package com.sainnt.netshop.api.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
class CommonApiAuthApplication

fun main(args: Array<String>) {
	runApplication<CommonApiAuthApplication>(*args)
}
