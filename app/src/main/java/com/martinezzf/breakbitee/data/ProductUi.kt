package com.martinezzf.breakbitee.data

import kotlinx.serialization.Serializable

@Serializable
data class ProductOptionUi(
    val id: String,
    val label: String,
    val priceDeltaQ: Int = 0,
    val initiallySelected: Boolean = false
)

@Serializable
data class ProductParameterUi(
    val id: String,
    val name: String,
    val options: List<ProductOptionUi>
)

@Serializable
data class ProductDetailUi(
    val id: String,
    val name: String,
    val description: String,
    val basePriceQ: Int,
    val imageUrl: String = "",
    val parameters: List<ProductParameterUi> = emptyList(),
    val serviceName: String = "Servicio"

)
