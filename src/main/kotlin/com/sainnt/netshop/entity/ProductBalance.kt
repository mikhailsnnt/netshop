package com.sainnt.netshop.entity

import javax.persistence.*

@Entity
@IdClass(WarehouseOverProductId::class)
class ProductBalance(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id", nullable = false)
    val warehouse: Warehouse,
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    val product: Product,
    @Column(nullable = false)
    var amount: Long
)