package com.sainnt.netshop.entity

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class CartOverProduct(
        private val cart: Cart,
        private val product: Product,
) : Serializable