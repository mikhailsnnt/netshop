package com.sainnt.netshop.common.dto.request

data class CatalogCreateDto(
    val name: String,
    val parentId: Long? = null
)
