package com.sainnt.netshop.api.product.repository;

import com.sainnt.netshop.api.product.entity.Catalog
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface CatalogRepository : PagingAndSortingRepository<Catalog, Long> {
    fun findCatalogsByParentNull(pageable: Pageable): Page<Catalog>

    fun findByNameContaining(name: String?, pageable: Pageable): Page<Catalog>
}