package com.sainnt.netshop.common.dto.request

data class ProductUpdateDto(
    val name: String?,
    val catalogId: Long?,
    val price: Long?,
    val description: String? = null
)