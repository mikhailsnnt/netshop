package com.sainnt.netshop.entity

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*


@Data
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "productSequence", sequenceName = "PRODUCT_SEQ", initialValue = 1, allocationSize = 1)
class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSequence")
        private val id: Long,
        @Column(name = "product_name", nullable = false)
        private val name: String,
        @Column(nullable = false)
        private val price: Long,
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "description_id")
        private val productDescription: ProductDescription?
)