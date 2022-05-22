package com.sainnt.netshop.entity

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class CartOverProduct(
    val cart: Cart,
    val product: Product,
) : Serializable