/**
 * Modelo que representa un pedido desde el la perspectiva del servicio
 */

package com.martinezzf.breakbitee.data


data class ServiceOrderUi(
    val id: String, //id del pedido
    val estado: String, //estado del pedido
    val cliente: String, //nombre del cliente
    val cantidadProductos: Int, //cantidad de productos de la orden
    val total: String, //precio total del pedido
    val serviceId: String //id del restaurante, se utiliza en el fakeApi para identificar de que restaurante es el pedido
)
