package com.sainnt.netshop.entity

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class GeoPosition(
        private val latitude: String?,
        private val longitude: String?
) : Serializable