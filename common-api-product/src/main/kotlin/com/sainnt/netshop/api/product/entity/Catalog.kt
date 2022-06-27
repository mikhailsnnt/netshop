package com.sainnt.netshop.api.product.entity

import javax.persistence.*

@Entity
@SequenceGenerator(name = "catalogSequence", sequenceName = "CATALOG_SEQ", initialValue = 1, allocationSize = 1)
class Catalog(@Column(name="catalog_name",nullable = false) val name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogSequence")
    var id: Long? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    var parent: Catalog? = null

    @OneToMany(mappedBy= "parent",fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    var subCatalogs: List<Catalog> = listOf()
}