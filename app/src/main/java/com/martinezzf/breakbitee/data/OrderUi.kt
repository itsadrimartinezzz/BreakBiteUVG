package com.martinezzf.breakbitee.data

data class OrderUi(
    val id: String,
    val serviceName: String,
    val totalQ: Int,
    val productCount: Int,
    val dateTime: String,
    val status: String = "Entregado"
)
