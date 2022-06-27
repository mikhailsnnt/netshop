package com.sainnt.netshop.common.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class CommonSpringGatewayApplication

fun main(args: Array<String>) {
	runApplication<CommonSpringGatewayApplication>(*args)
}
