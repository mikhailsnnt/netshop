package com.sainnt.netshop.api.auth.redis

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("token")
class RefreshToken(
    @Id
    val userId: Long,
    val token: String
)