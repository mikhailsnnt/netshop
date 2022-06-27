package com.sainnt.netshop.repository.search

import com.sainnt.netshop.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ProductRepositoryCustom {
    fun search(name: String?, catalogId: Long?, description: String?, pageRequest: PageRequest): Page<Product>
}