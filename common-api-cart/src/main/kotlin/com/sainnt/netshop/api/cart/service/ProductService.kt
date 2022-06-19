package com.sainnt.netshop.api.cart.service

import com.sainnt.netshop.common.dto.crm.ProductDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient("COMMON-API-PRODUCT")
interface ProductService {
    @RequestMapping(method = [RequestMethod.GET], value =["/product/{ids}"])
    fun get(@PathVariable ids: Set<Long>): List<ProductDto>

    @RequestMapping(method = [RequestMethod.GET], value = ["/product/{id}"])
    fun get(@PathVariable id: Long): ProductDto
}