package com.sainnt.netshop.entity

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "warehouseSequence", sequenceName = "WAREHOUSE_SEQ", initialValue = 1, allocationSize = 1)
class Warehouse(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "warehouseSequence")
        private val id: Long,
        @Column(nullable = false)
        private val address: String,
        @Column(nullable = false)
        private val geoPosition: GeoPosition,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
        private val products: List<ProductBalance>
)