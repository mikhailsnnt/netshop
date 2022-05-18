package com.sainnt.netshop.entity

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@NoArgsConstructor
@Entity
@Table(name="user_address")
@SequenceGenerator(name = "userAddressSequence", sequenceName = "USER_ADDRESS_SEQ", initialValue = 1, allocationSize = 1)
class UserAddress(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userAddressSequence")
        private val id: Long,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="user_id", nullable = false)
        private val user: User,
        private val address: String,
        @Embedded
        private val geoPosition: GeoPosition?
)