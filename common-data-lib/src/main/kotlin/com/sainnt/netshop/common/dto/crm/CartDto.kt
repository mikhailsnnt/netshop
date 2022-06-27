package com.sainnt.netshop.common.dto.crm

data class CartDto(
    val userId: Long,
    val items: List<CartItem>
) {
    data class CartItem(
        val product: ProductDto,
        val amount: Int
    )
}
