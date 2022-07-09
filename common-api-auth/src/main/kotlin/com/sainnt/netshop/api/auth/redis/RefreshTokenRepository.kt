package com.sainnt.netshop.api.auth.redis

import org.springframework.data.keyvalue.repository.KeyValueRepository

interface RefreshTokenRepository : KeyValueRepository<RefreshToken, Long>