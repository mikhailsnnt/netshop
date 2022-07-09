package com.sainnt.netshop.api.userprofile.entity

import javax.persistence.*

@Entity
@Table(name = "UserInfo")
@SequenceGenerator(name = "userSequence", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1)
class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    var id: Long? = null

    @Column(name = "user_name", nullable = false)
    lateinit var name: String

    @Column(name = "user_surname", nullable = false)
    lateinit var surname: String

    @Column(name = "user_patronymic")
    var patronymic: String? = null

    @Column(nullable = false, unique = true)
    lateinit var  phoneNumber: String
    var email: String? = null

    @OneToOne(optional = false, orphanRemoval = true, fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    lateinit var credentials: Credentials

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id", nullable = false)],
        inverseJoinColumns = [JoinColumn(name = "role_id", nullable = false)],
        uniqueConstraints = [UniqueConstraint(columnNames = ["role_id", "user_id"])]
    )
    var roles: List<Role> = listOf()
}