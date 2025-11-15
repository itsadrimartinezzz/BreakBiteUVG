package com.martinezzf.breakbitee.data

import kotlinx.serialization.Serializable

@Serializable
data class UserOrderItemUi(
    val id: String,
    val name: String,
    val priceQ: Int,
    val imageUrl: String? = null,
    val quantity: Int = 1,
    val serviceId: String = "",
    val orderId: String

)
