package com.sainnt.netshop.repository.impl

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

@Repository
abstract class CriteriaSearchRepository {
    @PersistenceContext
    protected lateinit var entityManager: EntityManager

    fun <T> CriteriaQuery<T>.performPaginated(root: Root<T>, pageRequest: PageRequest): Page<T>{
        val builder = entityManager.criteriaBuilder
        val countQuery = builder.createQuery(Long::class.java)
        countQuery.select(builder.count(root))
        this.roots?.forEach(countQuery.roots::add)
        this.restriction?.let(countQuery::where)
        this.groupRestriction?.let(countQuery::having )
        this.groupList?.let(countQuery::groupBy)
        this.isDistinct?.let(countQuery::distinct)
        val totalCount = entityManager.createQuery(countQuery).singleResult
        if(totalCount < pageRequest.pageNumber*pageRequest.pageSize)
            return Page.empty(Pageable.ofSize(pageRequest.pageSize))

        val content =
             entityManager
                 .createQuery(this)
                 .setFirstResult(pageRequest.pageNumber * pageRequest.pageSize)
            .setMaxResults(pageRequest.pageSize).resultList
        return PageImpl(content,pageRequest,totalCount)
    }
}