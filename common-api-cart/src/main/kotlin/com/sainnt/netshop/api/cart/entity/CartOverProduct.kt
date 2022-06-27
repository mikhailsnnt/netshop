package com.sainnt.netshop.api.cart.entity

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class CartOverProduct(
    val userId: Long,
    val productId: Long,
) : Serializable