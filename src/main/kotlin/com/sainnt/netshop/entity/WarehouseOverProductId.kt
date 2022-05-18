package com.sainnt.netshop.entity

import java.io.Serializable
import javax.persistence.*

@Embeddable
class WarehouseOverProductId(
        private val warehouse: Warehouse,
        private val product: Product,
) : Serializable