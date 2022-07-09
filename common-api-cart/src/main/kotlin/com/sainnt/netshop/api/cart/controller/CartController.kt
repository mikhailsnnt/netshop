package com.sainnt.netshop.api.cart.controller

import com.sainnt.netshop.api.cart.service.CartService
import com.sainnt.netshop.common.dto.crm.CartDto
import com.sainnt.netshop.common.dto.request.cart.AddCartItemDto
import com.sainnt.netshop.common.dto.request.cart.DeleteCartItemDto
import com.sainnt.netshop.common.dto.request.cart.UpdateCartItemDto
import com.sainnt.netshop.jwt.utils.JwtTokenData
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/cart")
class CartController(private val cartService: CartService) {

    @GetMapping
    fun getCurrentUserCart(): ResponseEntity<CartDto> {
        return ResponseEntity.ok(cartService.getCart(getJwtUserId()))
    }

    @PostMapping("/item")
    fun addItem(@Valid @RequestBody addCartItemDto: AddCartItemDto){
        cartService.addItem(getJwtUserId(),addCartItemDto.productId,addCartItemDto.amount)
    }

    @PutMapping("/item")
    fun updateItem(@Valid @RequestBody updateCartItemDto: UpdateCartItemDto){
        cartService.addItem(getJwtUserId(),updateCartItemDto.productId,updateCartItemDto.amount)
    }

    @DeleteMapping("/item")
    fun deleteItem(@Valid @RequestBody deleteCartItemDto: DeleteCartItemDto){
        cartService.removeProduct(getJwtUserId(), deleteCartItemDto.productId)
    }

    fun getJwtUserId(): Long{
        return (SecurityContextHolder.getContext()
            .authentication
            .principal as JwtTokenData)
            .let { it.subject.toLong() }
    }
}