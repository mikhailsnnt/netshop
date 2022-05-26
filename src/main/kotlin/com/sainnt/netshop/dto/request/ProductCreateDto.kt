package com.sainnt.netshop.dto.request

data class ProductCreateDto(
    val name: String,
    val catalogId: Long,
    val price: Long,
    val description: String? = null
)