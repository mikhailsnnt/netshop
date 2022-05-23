package com.sainnt.netshop.repository;

import com.sainnt.netshop.entity.ProductDescription
import org.springframework.data.jpa.repository.JpaRepository

interface ProductDescriptionRepository : JpaRepository<ProductDescription, Long> {
}