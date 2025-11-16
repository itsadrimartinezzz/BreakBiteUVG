package com.martinezzf.breakbitee.ui.serviceScreens

import androidx.compose.ui.graphics.Color



data class ServiceHeaderUi(
    val name: String? = null,
    val tag: String? = null,
    val bannerColor: Color = Color(0xFF2E584A),
    val avatarColor: Color = Color(0xFF1D3B31),
    val logoUrl: String = ""
)


data class OrderItemUi(
    val id: String,
    val nombre: String,
    val precio: Int,
    val listo: Boolean,
    val imageUrl: String = ""
)

data class ProductUi(
    val id: String,
    val title: String,
    val price: String,
    val imageUrl: String = ""
)

data class CategoryUi(
    val id: String,
    val name: String,
    val products: List<ProductUi>
)


enum class ServiceTab {
    ORDERS, STORE
}