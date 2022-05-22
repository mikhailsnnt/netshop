package com.sainnt.netshop.entity

import javax.persistence.*


@Entity
@SequenceGenerator(name = "productSequence", sequenceName = "PRODUCT_SEQ", initialValue = 1, allocationSize = 1)
class Product(
    @Column(name = "product_name", nullable = false)
    var name: String,
    @Column(nullable = false)
    var price: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSequence")
    val id: Long? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "description_id")
    var productDescription: ProductDescription? = null
}