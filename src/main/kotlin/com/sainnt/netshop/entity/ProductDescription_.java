package com.sainnt.netshop.entity;


import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductDescription.class)
public abstract class ProductDescription_ {
    public static volatile SingularAttribute<ProductDescription, Integer> id;
    public static volatile SingularAttribute<ProductDescription, String> description;
    public static volatile SingularAttribute<ProductDescription, Product> product;
}