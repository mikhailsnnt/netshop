package com.sainnt.netshop.api.product.entity

import javax.persistence.*

@Entity
@Table(name = "product_description")
@SequenceGenerator(
    name = "productDescriptionSequence",
    sequenceName = "PRODUCT_DESCRIPTION_SEQ",
    initialValue = 1,
    allocationSize = 1
)
class ProductDescription(
    @Column(nullable = false)
    var description: String,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)
    val product: Product,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productDescriptionSequence")
    var id: Long? = null
}