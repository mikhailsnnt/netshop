package com.sainnt.netshop.api.product.repository;

import com.sainnt.netshop.api.product.entity.ProductDescription
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductDescriptionRepository : JpaRepository<ProductDescription, Long> {
    fun findByProductId(productId:Long): Optional<ProductDescription>
}