package com.martinezzf.breakbitee.data

data class ServiceOrderUi(
    val id: String,
    val estado: String,
    val cliente: String,
    val cantidadProductos: Int,
    val total: String
)
