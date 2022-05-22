package com.sainnt.netshop.entity


import javax.persistence.*

@Entity
@SequenceGenerator(name = "cartSequence", sequenceName = "CART_SEQ", initialValue = 1, allocationSize = 1)
class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartSequence")
    var id: Long? = null

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart")
    var items: MutableList<CartItem> = mutableListOf()
}