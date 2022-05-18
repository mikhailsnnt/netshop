package com.sainnt.netshop.entity

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@NoArgsConstructor
@Entity
@Table(name = "UserInfo")
@SequenceGenerator(name = "userSequence", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1)
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
        private val id: Long,
        @Column(name = "user_name", nullable = false)
        private val name: String,
        @Column(name = "user_surname", nullable = false)
        private val surname: String,
        @Column(name = "user_patronymic")
        private val patronymic: String?,
        @Column(nullable = false, unique = true)
        private val phoneNumber: String,
        private val email: String?,
        @OneToOne(optional = false, orphanRemoval = true, fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
        private val credentials: Credentials,
        @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE], mappedBy = "user")
        private val addresses: List<UserAddress>,
        @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
        private val cart: Cart,
        @ManyToMany(mappedBy = "users")
        private val roles: List<Role>
)