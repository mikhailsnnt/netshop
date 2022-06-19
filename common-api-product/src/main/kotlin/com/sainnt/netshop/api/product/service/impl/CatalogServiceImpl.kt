package com.sainnt.netshop.api.product.service.impl

import com.sainnt.netshop.api.product.entity.Catalog
import com.sainnt.netshop.api.product.service.CatalogService
import com.sainnt.netshop.api.product.util.retrieve
import com.sainnt.netshop.common.dto.crm.CatalogDto
import com.sainnt.netshop.api.product.repository.CatalogRepository
import com.sainnt.netshop.common.dto.request.CatalogCreateDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CatalogServiceImpl(
    private val catalogRepository: CatalogRepository
) : CatalogService {
    override fun create(catalogCreateDto: CatalogCreateDto): CatalogDto {
        val catalog = Catalog(catalogCreateDto.name)
        catalog.parent = catalogCreateDto.parentId?.let(catalogRepository::retrieve)
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
        return catalogRepository.retrieve(id).let(this::mapCatalogDtoAndIncludeChildren)
    }

    override fun getRootCatalogs(page: Int, pageSize: Int): Page<CatalogDto> {
        return catalogRepository.findCatalogsByParentNull(PageRequest.of(page,pageSize)).map(this::mapCatalogDtoAndIncludeChildren)
    }

    override fun deleteById(id: Long) {
        catalogRepository.retrieve(id).let(catalogRepository::delete)
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