package com.sainnt.netshop.api.product.controller

import com.sainnt.netshop.api.product.service.ProductService
import com.sainnt.netshop.common.dto.crm.ProductDto
import com.sainnt.netshop.common.dto.request.ProductCreateDto
import com.sainnt.netshop.common.dto.request.ProductUpdateDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/product")
class ProductController(private val productService: ProductService) {
    @GetMapping("/{productId}")
    fun getById(@PathVariable productId: Long): ResponseEntity<ProductDto> {
        return ResponseEntity.ok(productService.getById(productId))
    }

    @GetMapping("/{productId}/description")
    fun getDescription(@PathVariable productId: Long): ResponseEntity<String> {
        return ResponseEntity.ok(productService.getDescription(productId))
    }

    @GetMapping("/search")
    fun search(
        @NotBlank @RequestParam(required = false) name: String?,
        @NotBlank @RequestParam(required = false) catalogId: Long?,
        @NotBlank @RequestParam(required = false) description: String?,
        @Min(1, message = "Page number cannot be less then 1") @RequestParam(defaultValue = "1") page: Int,
        @Min(1, message = "Page size must be greater then 1") @RequestParam(
            required = false, defaultValue = "\${api.config.defaultPageSize}"
        ) pageSize: Int,
        @RequestParam(required = false) sortBy: String?,
        @RequestParam(defaultValue = "ASC") sortDirection: Sort.Direction
    ): ResponseEntity<Page<ProductDto>> {
        return ResponseEntity.ok(
            productService.search(
                name,
                catalogId,
                description,
                page-1,
                pageSize,
                sortBy,
                sortDirection
            )
        )
    }


    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    fun create(
        @RequestBody productCreateDto: ProductCreateDto
    ): ResponseEntity<ProductDto> {
        return ResponseEntity.ok(productService.create(productCreateDto))
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{productId}")
    fun update(
        @PathVariable productId: Long,
        @RequestBody updateDto: ProductUpdateDto
    ): ResponseEntity<ProductDto> {
        return ResponseEntity.ok(productService.update(productId, updateDto))
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{productId}")
    fun delete(
        @PathVariable productId: Long
    ): ResponseEntity<String>{
        productService.deleteById(productId)
        return ResponseEntity.ok("Product $productId deleted")
    }


}