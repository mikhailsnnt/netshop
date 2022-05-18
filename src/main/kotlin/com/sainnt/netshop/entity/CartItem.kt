package com.sainnt.netshop.entity

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@NoArgsConstructor
@Entity
@IdClass(CartOverProduct::class)
class CartItem(
        @Id
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "cart_id", nullable = false)
        private val cart: Cart,
        @Id
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "product_id", nullable = false)
        private val product: Product,
        @Column(nullable = false)
        private val amount: Int
)