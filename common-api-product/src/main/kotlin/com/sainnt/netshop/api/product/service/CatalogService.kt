package com.sainnt.netshop.api.product.service

import com.sainnt.netshop.common.dto.crm.CatalogDto
import com.sainnt.netshop.common.dto.request.CatalogCreateDto
import org.springframework.data.domain.Page

interface CatalogService {
    fun create(catalogCreateDto: CatalogCreateDto): CatalogDto

    fun search(name: String?, page: Int = 0, pageSize: Int): Page<CatalogDto>

    fun getById(id: Long): CatalogDto

    fun getRootCatalogs(page: Int = 0, pageSize: Int): Page<CatalogDto>

    fun deleteById(id: Long)
}
