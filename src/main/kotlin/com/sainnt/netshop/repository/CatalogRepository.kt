package com.sainnt.netshop.repository;

import com.sainnt.netshop.entity.Catalog
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface CatalogRepository : PagingAndSortingRepository<Catalog, Long> {
    fun findCatalogsByParentNull(pageable: Pageable): Page<Catalog>

    fun findByNameContaining(name: String?, pageable: Pageable): Page<Catalog>
}