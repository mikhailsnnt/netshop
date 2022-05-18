package com.sainnt.netshop.entity

import javax.persistence.*

@Entity
@IdClass(WarehouseOverProductId::class)
class ProductBalance(
        @Id
        @OneToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
        private val warehouse: Warehouse,
        @Id
        @OneToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "product_id", referencedColumnName = "id")
        private val product: Product,
        @Column(nullable = false)
        private val amount: Long
)