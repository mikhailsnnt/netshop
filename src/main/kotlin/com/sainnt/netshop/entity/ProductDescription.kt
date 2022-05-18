package com.sainnt.netshop.entity

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@NoArgsConstructor
@Entity
@Table(name="product_description")
@SequenceGenerator(name = "productDescriptionSequence", sequenceName = "PRODUCT_DESCRIPTION_SEQ", initialValue = 1, allocationSize = 1)
class ProductDescription(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productDescriptionSequence")
        private val id: Long,
        @Column(nullable = false)
        private val description: String
)