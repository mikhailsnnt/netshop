package com.sainnt.netshop.api.cart.repository;

import com.sainnt.netshop.api.cart.entity.CartItem
import com.sainnt.netshop.api.cart.entity.CartOverProduct
import org.springframework.data.jpa.repository.JpaRepository

interface CartItemRepository : JpaRepository<CartItem, CartOverProduct> {
    fun findAllByUserId(userId:Long): List<CartItem>
}