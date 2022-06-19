package com.sainnt.netshop.api.product.service.impl

import com.sainnt.netshop.api.product.entity.Product
import com.sainnt.netshop.api.product.entity.ProductDescription
import com.sainnt.netshop.api.product.repository.CatalogRepository
import com.sainnt.netshop.api.product.repository.ProductDescriptionRepository
import com.sainnt.netshop.api.product.repository.ProductRepository
import com.sainnt.netshop.api.product.service.ProductService
import com.sainnt.netshop.api.product.util.retrieve
import com.sainnt.netshop.common.dto.crm.ProductDto
import com.sainnt.netshop.common.dto.request.ProductCreateDto
import com.sainnt.netshop.common.dto.request.ProductUpdateDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val productDescriptionRepository: ProductDescriptionRepository,
    private val catalogRepository: CatalogRepository
) : ProductService {

    @Transactional
    override fun create(createDto: ProductCreateDto): ProductDto {
        val product = Product(createDto.name,createDto.price, catalogRepository.retrieve(createDto.catalogId))
        productRepository.save(product)
        createDto.description?.let {
            productDescriptionRepository.save(ProductDescription(it, product))
        }
        return mapToDto(product)
    }

    override fun search(
        name: String?,
        catalogId: Long?,
        description: String?,
        page: Int,
        pageSize: Int,
        sortBy: String?,
        sortDirection: Sort.Direction
    ): Page<ProductDto> {
        val pageRequest =
            if (sortBy != null) PageRequest.of(page, pageSize, Sort.by(sortDirection, sortBy))
            else PageRequest.of(page, pageSize)
        return productRepository
            .search(name, catalogId, description, pageRequest)
            .map(this::mapToDto)
    }

    override fun getDescription(productId: Long): String {
        return productDescriptionRepository
                .findById(productId).orElse(null)?.description ?: "No description provided"
    }

    override fun getById(id: Long): ProductDto {
        return productRepository.retrieve(id).let(this::mapToDto)
    }

    override fun getByIds(ids: Set<Long>): List<ProductDto> {
        return productRepository.findAllById(ids).map(this::mapToDto)
    }

    override fun update(id: Long, updateDto: ProductUpdateDto): ProductDto {
        return productRepository.retrieve(id).let {
            updateDto.name?.apply{it.name=this}
            updateDto.catalogId?.apply {it.catalog = catalogRepository.retrieve(this)}
            updateDto.price?.apply{it.price = this}
            if(updateDto.description != null) {
                if(it.description != null)
                    it.description!!.description = updateDto.description!!
                else
                    it.description = ProductDescription(updateDto.description!!, it)
            }
            mapToDto(it)
        }
    }

    override fun deleteById(id: Long) {
        productRepository.delete(productRepository.retrieve(id))
    }

    private fun mapToDto(product: Product): ProductDto {
        return ProductDto(product.id!!, product.name, product.catalog.id!!, product.price)
    }

}