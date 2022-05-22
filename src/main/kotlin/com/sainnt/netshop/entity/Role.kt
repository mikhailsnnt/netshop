package com.sainnt.netshop.entity

import javax.persistence.*

@Entity
@SequenceGenerator(name = "roleSequence", sequenceName = "ROLE_SEQ", initialValue = 1, allocationSize = 1)
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSequence")
    val id: Long,
    @Column(name = "role_name", nullable = false, unique = true)
    val name: String,
)