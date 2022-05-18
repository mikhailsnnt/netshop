package com.sainnt.netshop.entity

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "cartSequence", sequenceName = "CART_SEQ", initialValue = 1, allocationSize = 1)
class Cart(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartSequence")
        private val id: Long,
        @OneToOne
        @JoinColumn(name = "user_id", nullable = false)
        private val user: User,
        @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart")
        private val items: List<CartItem>
)