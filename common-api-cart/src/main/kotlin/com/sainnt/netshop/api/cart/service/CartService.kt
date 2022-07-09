package com.sainnt.netshop.api.cart.service

import com.sainnt.netshop.common.dto.crm.CartDto

interface CartService {
    fun getCart(userId: Long): CartDto

    fun addItem(userId: Long, productId: Long, amount: Int)

    fun updateItem(userId: Long, productId:Long, amount: Int)

    fun removeProduct(userId: Long, productId: Long)
}