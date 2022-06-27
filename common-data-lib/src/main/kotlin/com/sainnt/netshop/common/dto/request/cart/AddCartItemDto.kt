package com.sainnt.netshop.common.dto.request.cart

import javax.validation.constraints.Min

data class AddCartItemDto(
    @Min(1,message="Bad id")
    val productId: Long,
    @Min(1,message="Cannot add less then 1 item")
    val amount: Int
)
