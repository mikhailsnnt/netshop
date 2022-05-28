package com.sainnt.netshop.repository;

import com.sainnt.netshop.entity.ProductDescription
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductDescriptionRepository : JpaRepository<ProductDescription, Long> {
    fun findByProductId(productId:Long): Optional<ProductDescription>
}