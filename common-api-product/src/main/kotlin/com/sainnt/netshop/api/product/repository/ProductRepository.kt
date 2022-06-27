package com.sainnt.netshop.api.product.repository;

import com.sainnt.netshop.api.product.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>, ProductRepositoryCustom {
}