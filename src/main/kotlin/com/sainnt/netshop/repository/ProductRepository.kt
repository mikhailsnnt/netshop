package com.sainnt.netshop.repository;

import com.sainnt.netshop.entity.Product
import com.sainnt.netshop.repository.search.ProductRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository


interface ProductRepository : JpaRepository<Product, Long>, ProductRepositoryCustom {
}