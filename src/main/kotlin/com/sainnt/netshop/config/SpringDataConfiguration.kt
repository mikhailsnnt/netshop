package com.sainnt.netshop.config

import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["com.sainnt.netshop.repository.impl"])
class SpringDataConfiguration {
}