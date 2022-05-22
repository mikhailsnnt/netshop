package com.sainnt.netshop.entity

import javax.persistence.*

@Entity
@SequenceGenerator(name = "credentialsSequence", sequenceName = "CREDENTIALS_SEQ", initialValue = 1, allocationSize = 1)
class Credentials(
    val credentials: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credentialsSequence")
    val id: Long? = null
}