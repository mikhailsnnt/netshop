package com.sainnt.netshop.service.impl

import com.sainnt.netshop.dto.ProductDto
import com.sainnt.netshop.dto.request.ProductCreateDto
import com.sainnt.netshop.dto.request.ProductUpdateDto
import com.sainnt.netshop.entity.Product
import com.sainnt.netshop.entity.ProductDescription
import com.sainnt.netshop.repository.CatalogRepository
import com.sainnt.netshop.repository.ProductDescriptionRepository
import com.sainnt.netshop.repository.ProductRepository
import com.sainnt.netshop.service.ProductService
import com.sainnt.netshop.util.retrieve
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
            .findByProductId(productId).orElse(null)
            ?.let { it.description } ?: "No description provided"
    }

    override fun getById(id: Long): ProductDto {
        return productRepository.retrieve(id).let(this::mapToDto)
    }

    override fun update(id: Long, updateDto: ProductUpdateDto): ProductDto {
        return productRepository.retrieve(id).let {
            if(updateDto.name != null)
                it.name = updateDto.name
            if(updateDto.catalogId != null)
                it.catalog = catalogRepository.retrieve(updateDto.catalogId)
            if(updateDto.price != null)
                it.price = updateDto.price
            if(updateDto.description != null) {
                if(it.productDescription != null)
                    it.productDescription!!.description = updateDto.description
                else
                    it.productDescription = ProductDescription(updateDto.description, it)
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