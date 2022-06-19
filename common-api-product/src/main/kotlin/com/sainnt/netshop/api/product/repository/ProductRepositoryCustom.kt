package com.sainnt.netshop.api.product.repository

import com.sainnt.netshop.api.product.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ProductRepositoryCustom {
    fun search(name: String?, catalogId: Long?, description: String?, pageRequest: PageRequest): Page<Product>
}