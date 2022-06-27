package com.sainnt.netshop.repository.impl

import com.sainnt.netshop.entity.Product
import com.sainnt.netshop.entity.ProductDescription_
import com.sainnt.netshop.entity.Product_
import com.sainnt.netshop.repository.search.ProductRepositoryCustom
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
        val where = mutableListOf<Predicate>()
        if (name != null)
            where.add(cb.like(cb.lower(from.get("name")), "%${name.lowercase()}%"))
        if (catalogId != null)
            where.add(cb.equal(from.get<Long>("catalogId"), catalogId))
        if (description != null) {
            val joinDescription = from.join(Product_.description, JoinType.INNER)
            where.add(cb.like(cb.lower(joinDescription.get(ProductDescription_.description)), "%${description.lowercase()}%"))
        }
        query.orderBy(QueryUtils.toOrders(pageRequest.sort, from, cb))
        return query
            .performPaginated(from, pageRequest)
    }
}