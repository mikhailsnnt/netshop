package com.sainnt.netshop.entity

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "credentialsSequence", sequenceName = "CREDENTIALS_SEQ", initialValue = 1, allocationSize = 1)
class Credentials(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credentialsSequence")
        private val id: Long,
        private val credentials: String
)