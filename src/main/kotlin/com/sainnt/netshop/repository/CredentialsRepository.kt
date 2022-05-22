package com.sainnt.netshop.repository;

import com.sainnt.netshop.entity.Credentials
import org.springframework.data.jpa.repository.JpaRepository

interface CredentialsRepository : JpaRepository<Credentials, Long> {
}