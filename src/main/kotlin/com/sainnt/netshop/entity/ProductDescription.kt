package com.sainnt.netshop.entity

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
    val description: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productDescriptionSequence")
    var id: Long? = null
}