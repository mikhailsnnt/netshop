package com.sainnt.netshop.api.product.config

import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["com.sainnt.netshop.api.product.repository.impl"])
class SpringDataConfig
