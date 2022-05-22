package com.sainnt.netshop.repository;

import com.sainnt.netshop.entity.Cart
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<Cart, Long> {
}