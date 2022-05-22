package com.sainnt.netshop.entity

import javax.persistence.*

@Entity //Shall move to NON SQL Database
@SequenceGenerator(name = "catalogSequence", sequenceName = "CATALOG_SEQ", initialValue = 1, allocationSize = 1)
class Catalog(@Column(nullable = false) val name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogSequence")
    var id: Long? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    var parent: Catalog? = null

    @OneToMany(mappedBy= "parent",fetch = FetchType.LAZY)
    var subCatalogs: List<Catalog> = listOf()
}