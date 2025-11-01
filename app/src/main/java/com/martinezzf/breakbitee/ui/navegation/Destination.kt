package com.martinezzf.breakbitee.ui.navegation



import kotlinx.serialization.Serializable

@Serializable object LoginDestination
@Serializable object SignUpDestination
@Serializable object MainDestination

// Flujo estudiantes (ya existente)
@Serializable data class ServiceDestination(val serviceId: String)
@Serializable data class ProductDestination(val productJson: String, val serviceId: String)
@Serializable data class OrderDetailDestination(val orderId: String)
@Serializable object NotificationsDestination

// === NUEVO: Flujo establecimiento ===
@Serializable object ServiceOrdersDestination              // pantalla principal: pedidos
@Serializable object ServiceStoreDestination               // pantalla: tienda/productos
@Serializable data class ServiceOrderDetailDestination(val orderId: String) // detalle de pedido
@Serializable object NewProductDestination                 // crear/editar producto


