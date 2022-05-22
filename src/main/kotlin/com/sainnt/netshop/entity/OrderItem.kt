package com.sainnt.netshop.entity

import javax.persistence.*

@Entity
@Table(name = "order_item")
@SequenceGenerator(name = "orderItemSequence", sequenceName = "CART_SEQ", initialValue = 1, allocationSize = 1)
class OrderItem(
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    val order: Order,
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    val product: Product,
    @Column(nullable = false)
    val amount: Int,
    @Column(nullable = false)
    val price: Long
){
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemSequence")
    var id: Long? = null
}