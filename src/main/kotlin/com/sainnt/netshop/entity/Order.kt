package com.sainnt.netshop.entity

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "order_info")
@SequenceGenerator(name = "orderSequence", sequenceName = "ORDER_SEQ", initialValue = 1, allocationSize = 1)
class Order(
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    val user: User,
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_address_id", nullable = false)
    var address: UserAddress,
    @Column(nullable = false) // Default value is Now()
    val creationTime: Timestamp
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSequence")
    val id: Long? = null

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id")
    var items: List<OrderItem> = listOf()

    var completionTime: Timestamp? = null

    @Enumerated(EnumType.STRING)
    var completionResult: OrderCompletionResult? = null
}