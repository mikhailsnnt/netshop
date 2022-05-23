package com.sainnt.netshop.repository;

import com.sainnt.netshop.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
}