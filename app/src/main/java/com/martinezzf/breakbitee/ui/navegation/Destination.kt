package com.martinezzf.breakbitee.ui.navegation

import kotlinx.serialization.Serializable

@Serializable
object LoginDestination

@Serializable
object SignUpDestination

@Serializable
object MainDestination

@Serializable
object ServiceOrdersDestination

@Serializable
data class ServiceOrderDetailDestination(val orderId: String)

@Serializable
data class ProductDestination(val productJson: String, val serviceId: String)

@Serializable
object NewProductDestination

@Serializable
data class ServiceDestination(val serviceId: String)


@Serializable
data class OrderDetailDestination(
    val orderId: String,
    val serviceName: String
)


@Serializable
object NotificationsDestination

@Serializable
object ServiceStoreDestination
