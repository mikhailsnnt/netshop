package com.sainnt.netshop.service.impl

import com.sainnt.netshop.dto.CatalogDto
import com.sainnt.netshop.entity.Catalog
import com.sainnt.netshop.exception.NotFoundException
import com.sainnt.netshop.repository.CatalogRepository
import com.sainnt.netshop.service.CatalogService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CatalogServiceImpl(
    private val catalogRepository: CatalogRepository
) : CatalogService {
    override fun create(name: String, parentId: Long?): CatalogDto {
        val catalog = Catalog(name)
        catalog.parent = parentId?.let(this::retrieveById) ?: null
        catalogRepository.save(catalog)
        return mapToDto(catalog)
    }

    override fun search(name: String?, page: Int, pageSize: Int): Page<CatalogDto> {
        return if (name != null)
            catalogRepository.findByNameContaining(name, PageRequest.of(page, pageSize)).map(this::mapToDto)
        else
            catalogRepository.findAll(PageRequest.of(page,pageSize)).map(this::mapToDto)
    }

    override fun getById(id: Long): CatalogDto {
        return retrieveById(id).let(this::mapCatalogDtoAndIncludeChildren)
    }

    override fun getRootCatalogs(page: Int, pageSize: Int): Page<CatalogDto> {
        return catalogRepository.findCatalogsByParentNull(PageRequest.of(page,pageSize)).map(this::mapCatalogDtoAndIncludeChildren)
    }

    override fun deleteById(id: Long) {
        retrieveById(id).let(catalogRepository::delete)
    }

    private fun retrieveById(id: Long): Catalog {
        return catalogRepository.findById(id).orElseThrow { NotFoundException("Catalog with id $id not found") }
    }

    private fun mapCatalogDtoAndIncludeChildren(catalog: Catalog): CatalogDto {
        return mapToDto(catalog).also {
            it.subCatalogs = catalog.subCatalogs.map(this::mapToDto)
        }
    }

    private fun mapToDto(catalog: Catalog): CatalogDto {
        return CatalogDto(id = catalog.id!!, name = catalog.name)
    }
}