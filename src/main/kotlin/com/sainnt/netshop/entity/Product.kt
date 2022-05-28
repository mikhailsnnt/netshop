package com.sainnt.netshop.entity

import javax.persistence.*


@Entity
@SequenceGenerator(name = "productSequence", sequenceName = "PRODUCT_SEQ", initialValue = 1, allocationSize = 1)
class Product(
    @Column(name = "product_name", nullable = false)
    var name: String,
    @Column(nullable = false)
    var price: Long,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="catalog_id", nullable = false)
    var catalog: Catalog
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSequence")
    val id: Long? = null

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "product")
    var productDescription: ProductDescription? = null

}