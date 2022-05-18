package com.sainnt.netshop.entity

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "roleSequence", sequenceName = "ROLE_SEQ", initialValue = 1, allocationSize = 1)
class Role (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSequence")
        private val id: Long,
        @Column(name="role_name",nullable = false, unique = true)
        private val name: String,
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                name="user_roles",
                joinColumns = [JoinColumn(name="role_id", nullable = false)],
                inverseJoinColumns = [JoinColumn(name="user_id", nullable = false)],
                uniqueConstraints = [UniqueConstraint(columnNames = ["role_id","user_id"])]
        )
        private val users: List<User>
)