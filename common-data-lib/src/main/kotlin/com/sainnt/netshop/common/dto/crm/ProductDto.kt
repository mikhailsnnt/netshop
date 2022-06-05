package com.sainnt.netshop.common.dto.crm

data class ProductDto(
    val id: Long,
    val name:String,
    val catalogId: Long,
    val price: Long
)