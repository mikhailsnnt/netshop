package com.sainnt.netshop.util

import com.sainnt.netshop.exception.NotFoundException
import org.springframework.data.repository.CrudRepository
import java.lang.reflect.ParameterizedType

fun <T,ID> CrudRepository<T,ID>.retrieve(id: ID): T{
    return this.findById(id).orElseThrow{
        val className = (this.javaClass.genericSuperclass as? ParameterizedType)?.actualTypeArguments?.get(0)
        throw NotFoundException("Entity ${className?.typeName} with id $id not found")
    }
}