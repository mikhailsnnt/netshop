package com.sainnt.netshop.entity

import java.io.Serializable
import javax.persistence.*

@Embeddable
class WarehouseOverProductId(
         val warehouse: Warehouse,
         val product: Product,
) : Serializable