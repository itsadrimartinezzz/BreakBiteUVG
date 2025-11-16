/**
 * Define todas las rutas que se utiliza para la navegacion con serializable
 */

package com.martinezzf.breakbitee.ui.navegation

import kotlinx.serialization.Serializable

//Pantalla de inicio de sesion
@Serializable
object LoginDestination

//pantalla de registro
@Serializable
object SignUpDestination

//pantalla principal para el usuario, UserHomeScreen
@Serializable
object MainDestination

//Pantalla de gestion de pedidos recibidos, ServiceOrdersDestination
@Serializable
object ServiceOrdersDestination

//Pantalla de detalle del pedido, ServiceOrderDetailDestination
@Serializable
data class ServiceOrderDetailDestination(val orderId: String)

//Pantalla de informacion del producto, ProductDestination
@Serializable
data class ProductDestination(val productJson: String, val serviceId: String)

//Pantalla de creacion de prodcuto, NewProductDestination
@Serializable
object NewProductDestination   // 👈 DESTINO NUEVO (YA ESTÁ)

//Pantalla de menu del restaurante, ServiceDestination
@Serializable
data class ServiceDestination(val serviceId: String)

//Pantalla de detalle del pedido desde la vista del usuario, OrderDetailDestination
@Serializable
data class OrderDetailDestination(val orderId: String)

//Pnatalla de notificaciones, NotificationsDestination
@Serializable
object NotificationsDestination


