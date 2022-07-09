package com.sainnt.netshop.common.dto.request.cart

import javax.validation.constraints.Min

class UpdateCartItemDto(
    @Min(1,message="Bad id")
    val productId: Long,
    @Min(0, message="New amount cannot be less then 1")
    val amount: Int
)