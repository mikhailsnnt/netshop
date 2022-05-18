package com.sainnt.netshop.entity

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_item")
@SequenceGenerator(name = "orderItemSequence", sequenceName = "CART_SEQ", initialValue = 1, allocationSize = 1)
class OrderItem(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemSequence")
        private val id: Long,
        @OneToOne(fetch = FetchType.LAZY, optional = false)
        private val order: Order,
        @OneToOne(fetch = FetchType.LAZY, optional = false)
        private val product: Product,
        @Column(nullable = false)
        private val amount: Int,
        @Column(nullable = false)
        private val price: Long
)