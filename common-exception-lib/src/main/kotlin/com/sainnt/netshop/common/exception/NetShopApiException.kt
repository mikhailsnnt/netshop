package com.sainnt.netshop.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class NetShopApiException(message:String? = null): CustomException(message, HttpStatus.BAD_REQUEST)