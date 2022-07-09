package com.sainnt.netshop.api.cart.service.impl

import com.sainnt.netshop.api.cart.entity.CartItem
import com.sainnt.netshop.api.cart.entity.CartOverProduct
import com.sainnt.netshop.api.cart.repository.CartItemRepository
import com.sainnt.netshop.api.cart.service.CartService
import com.sainnt.netshop.api.cart.service.ProductService
import com.sainnt.netshop.common.dto.crm.CartDto
import com.sainnt.netshop.common.dto.crm.ProductDto
import com.sainnt.netshop.common.exception.NotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CartServiceImpl(
    private val cartItemRepository: CartItemRepository,
    private val productService: ProductService
    ) : CartService {
    override fun getCart(userId: Long): CartDto {
        val cartItems = cartItemRepository.findAllByUserId(userId)
        val productIds = cartItems.map(CartItem::productId)
        val idOverProductDto = mutableMapOf<Long,ProductDto>()

        if (productIds.size == 1)
            productService.get(productIds[0]).let { idOverProductDto[it.id] = it  }
        else if(productIds.isNotEmpty())
            productService.get(HashSet(productIds)).forEach { idOverProductDto[it.id] = it  }

        return CartDto(
            userId,
            items = cartItems.mapNotNull {
                cartItem->
                idOverProductDto[cartItem.productId]?.let{
                    productDto -> CartDto.CartItem(productDto, cartItem.amount)
                }
            }
        )
    }

    override fun addItem(userId: Long, productId: Long, amount: Int) {
        cartItemRepository.findById(CartOverProduct(userId,productId))
            .map {
                it.amount += amount
                it
            }
            .orElse(CartItem(userId, productId, amount))
            .let(cartItemRepository::save)
    }

    override fun updateItem(userId: Long, productId: Long, amount: Int) {
        if (amount == 0)
            removeProduct(userId, productId)
        else {
            cartItemRepository.findById(CartOverProduct(userId, productId))
                .map {
                    it.amount = amount
                    it
                }
                .orElse(CartItem(userId, productId, amount))
                .let(cartItemRepository::save)
        }
    }

    override fun removeProduct(userId: Long, productId: Long) {
        retrieveItem(userId, productId).let(cartItemRepository::delete)
    }


    private fun retrieveItem(userId: Long, productId: Long): CartItem {
        return cartItemRepository
            .findById(CartOverProduct(userId, productId))
            .orElseThrow { NotFoundException("Product $productId not present in cart of user $userId") }
    }
}