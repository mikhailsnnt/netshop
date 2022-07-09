package com.sainnt.netshop.api.cart.entity

import javax.persistence.*

@Entity
@IdClass(CartOverProduct::class)
class CartItem(
    @Id
    @Column(name = "user_id", nullable = false)
    val userId: Long,
    @Id
    @Column(name="product_id", nullable = false)
    val productId:Long,
    @Column(nullable = false)
    var amount: Int
)
