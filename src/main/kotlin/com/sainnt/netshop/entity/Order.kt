package com.sainnt.netshop.entity

import lombok.Data
import lombok.NoArgsConstructor
import java.sql.Timestamp
import javax.persistence.*

@Data
@NoArgsConstructor
@Entity
@Table(name="order_info")
@SequenceGenerator(name = "orderSequence", sequenceName = "ORDER_SEQ", initialValue = 1, allocationSize = 1)
class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSequence")
        private val id: Long,
        @OneToOne(fetch = FetchType.LAZY, optional = false)
        private val user: User,
        @OneToMany(fetch = FetchType.EAGER, mappedBy = "id")
        private val items: List<OrderItem>,
        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "destination_address_id", nullable = false)
        private val address: UserAddress,
        @Column(nullable = false) // Default value is Now()
        private val creationTime: Timestamp,
        private val completionTime: Timestamp,
        @Enumerated(EnumType.STRING)
        private val completionResult: OrderCompletionResult
)