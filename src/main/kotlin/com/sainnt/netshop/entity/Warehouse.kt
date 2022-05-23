package com.sainnt.netshop.entity

import javax.persistence.*

@Entity
@SequenceGenerator(name = "warehouseSequence", sequenceName = "WAREHOUSE_SEQ", initialValue = 1, allocationSize = 1)
class Warehouse(

    @Column(nullable = false)
    val address: String,
    @Column(nullable = false)
    val geoPosition: GeoPosition
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "warehouseSequence")
    var id: Long? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
    var products: MutableList<ProductBalance> = mutableListOf()
}