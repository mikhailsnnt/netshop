package com.sainnt.netshop.entity

import javax.persistence.*

@Entity
@Table(name = "user_address")
@SequenceGenerator(
    name = "userAddressSequence",
    sequenceName = "USER_ADDRESS_SEQ",
    initialValue = 1,
    allocationSize = 1
)
class UserAddress(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,
    @Column(nullable = false)
    val address: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userAddressSequence")
    var id: Long? = null

    @Embedded
    var geoPosition: GeoPosition? = null
}