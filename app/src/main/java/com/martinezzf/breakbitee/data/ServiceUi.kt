package com.martinezzf.breakbitee.data

import kotlinx.serialization.Serializable

@Serializable
data class ServiceUi(
    val id: String,
    val name: String,
    val imageUrl: String,
    val bannerUrl: String,
    val time: String
)

