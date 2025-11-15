package com.martinezzf.breakbitee.data

import kotlinx.serialization.Serializable

@Serializable
data class UserOrderItemUi(
    val id: String,
    val name: String,
    val priceQ: Int,
    val imageUrl: String? = null,
    val quantity: Int = 1,

    // 🔥 NECESARIO PARA NAVEGATION
    val serviceId: String = "",
    val serviceName: String = "",   // <--- AGREGADO

    val basePriceQ: Int = priceQ,    // <--- AGREGADO (precio base sin multiplicar extras)

    val orderId: String
)
