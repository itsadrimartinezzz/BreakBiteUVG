package com.martinezzf.breakbitee.data

import kotlinx.serialization.Serializable

/**
 * Opción de producto (por ejemplo: tamaño, extra, etc.)
 */
@Serializable
data class ProductOptionUi(
    val id: String,
    val name: String,
    val extra: Int = 0,                 // costo adicional en quetzales
    val initiallySelected: Boolean = false
)

/**
 * Parámetro configurable de un producto (por ejemplo: tamaño, tipo de leche, etc.)
 */
@Serializable
data class ProductParameterUi(
    val id: String,
    val name: String,
    val options: List<ProductOptionUi>
)

/**
 * Detalle completo de un producto (para mostrar en la pantalla de detalle)
 */
@Serializable
data class ProductDetailUi(
    val id: String,
    val name: String,
    val description: String,
    val basePriceQ: Int,                // precio base
    val imageUrl: String = "",
    val parameters: List<ProductParameterUi> = emptyList(),
    val serviceName: String = "Servicio"
)
