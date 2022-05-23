package com.sainnt.netshop.entity

import javax.persistence.*

@Entity
@IdClass(CartOverProduct::class)
class CartItem(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    val cart: Cart,
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product,
    @Column(nullable = false)
    var amount: Int
)
