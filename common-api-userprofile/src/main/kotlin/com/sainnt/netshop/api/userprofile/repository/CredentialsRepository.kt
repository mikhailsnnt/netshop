package com.sainnt.netshop.api.userprofile.repository;

import com.sainnt.netshop.api.userprofile.entity.Credentials
import org.springframework.data.jpa.repository.JpaRepository

interface CredentialsRepository : JpaRepository<Credentials, Long> {
}