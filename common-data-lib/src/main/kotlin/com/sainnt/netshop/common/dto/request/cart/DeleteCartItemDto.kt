package com.sainnt.netshop.common.dto.request.cart

import javax.validation.constraints.Min

data class DeleteCartItemDto(
    @Min(1,message="Bad id")
    val productId: Long
)