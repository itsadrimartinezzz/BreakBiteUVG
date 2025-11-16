/**
 * Modelo que representa un pedido completo en la aplicacion.
 * La diferenccia de OrderItemUi es que este describe cada producto dentro del pedido,
 * OrderUi describe el pedido entero.
 */

package com.martinezzf.breakbitee.data

//Representa un pedido completo que realizo el usuario en la aplicacion.
data class OrderUi(
    val id: String, //id del pedido
    val serviceName: String, //Nombre del restaurante donde se hizo el pedido.
    val totalQ: Int, //Monto total del pedido
    val productCount: Int, //Cantidad total de productos dentro de la orden
    val dateTime: String, //Fecha y hora que se realizo el pedido
    val status: String //Estado del pedido.
)
