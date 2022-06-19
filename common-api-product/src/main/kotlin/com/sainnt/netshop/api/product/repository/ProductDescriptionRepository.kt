package com.sainnt.netshop.api.product.repository;

import com.sainnt.netshop.api.product.entity.ProductDescription
import org.springframework.data.jpa.repository.JpaRepository

interface ProductDescriptionRepository : JpaRepository<ProductDescription, Long> {
}