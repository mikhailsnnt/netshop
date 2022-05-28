package com.sainnt.netshop.entity;


import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {
    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, Long> price;
    public static volatile SingularAttribute<Product, Cart> catalog;
    public static volatile SingularAttribute<Product, ProductDescription> description;
}
