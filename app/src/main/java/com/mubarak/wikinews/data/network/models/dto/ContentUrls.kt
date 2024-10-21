package com.mubarak.wikinews.data.network.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContentUrls(
    @SerialName("mobile")
    val mobile: Mobile
)