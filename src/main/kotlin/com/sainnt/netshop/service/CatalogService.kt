package com.sainnt.netshop.service

import com.sainnt.netshop.dto.CatalogDto
import org.springframework.data.domain.Page

interface CatalogService {
    fun create(name:String, parentId: Long? = null): CatalogDto

    fun search(name: String?, page: Int = 0, pageSize: Int): Page<CatalogDto>

    fun getById(id: Long): CatalogDto

    fun getRootCatalogs(page: Int = 0, pageSize: Int): Page<CatalogDto>

    fun deleteById(id: Long)
}