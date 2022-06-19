package com.sainnt.netshop.api.product.service

import com.sainnt.netshop.common.dto.crm.ProductDto
import com.sainnt.netshop.common.dto.request.ProductCreateDto
import com.sainnt.netshop.common.dto.request.ProductUpdateDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort


interface ProductService {
    fun create(createDto: ProductCreateDto): ProductDto

    fun search(
        name: String?, catalogId: Long?, description: String?,
        page: Int = 0, pageSize: Int,
        sortBy: String? = null, sortDirection: Sort.Direction = Sort.Direction.ASC
    ): Page<ProductDto>

    fun getDescription(productId: Long): String

    fun getById(id: Long): ProductDto

    fun update(id: Long, updateDto: ProductUpdateDto): ProductDto

    fun deleteById(id: Long)
}