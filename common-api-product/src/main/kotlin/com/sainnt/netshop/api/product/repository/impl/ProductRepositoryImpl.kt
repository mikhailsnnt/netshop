package com.sainnt.netshop.api.product.repository.impl

import com.sainnt.netshop.api.product.entity.Catalog
import com.sainnt.netshop.api.product.entity.Product
import com.sainnt.netshop.api.product.entity.ProductDescription
import com.sainnt.netshop.api.product.repository.ProductRepositoryCustom
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.query.QueryUtils
import org.springframework.stereotype.Component
import javax.persistence.criteria.JoinType
import javax.persistence.criteria.Predicate

@Component
class ProductRepositoryImpl : CriteriaSearchRepository(), ProductRepositoryCustom {
    override fun search(
        name: String?,
        catalogId: Long?,
        description: String?,
        pageRequest: PageRequest
    ): Page<Product> {
        val cb = entityManager.criteriaBuilder
        val query = cb.createQuery(Product::class.java)
        val from = query.from(Product::class.java)
        val predicates = mutableListOf<Predicate>()
        if (name != null)
            predicates.add(cb.like(cb.lower(from.get("name")), "%${name.lowercase()}%"))
        if (catalogId != null)
            predicates.add(cb.equal(from.get<Catalog>("catalog").get<Long>("id"), catalogId))
        if (description != null) {
            val joinDescription = from.join<Product,ProductDescription>("description", JoinType.INNER)
            predicates.add(cb.like(cb.lower(joinDescription.get("description")), "%${description.lowercase()}%"))
        }
        query.where(*predicates.toTypedArray())
        query.orderBy(QueryUtils.toOrders(pageRequest.sort, from, cb))
        return query
            .performPaginated(from, pageRequest)
    }
}