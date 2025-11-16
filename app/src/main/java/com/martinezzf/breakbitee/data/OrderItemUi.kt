/**
 * Define una data class llamada UserOrderItemUi, representando un producto dentro de un pedido hecho por el usuario
 * Marcada con @Serializable.
 */


package com.martinezzf.breakbitee.data

import kotlinx.serialization.Serializable

@Serializable
data class UserOrderItemUi(
    //Atributos del producto
    val id: String, //Id del producto
    val orderId: String,
    val name: String, //Nombre del producto
    val priceQ: Int, //Precio total del producto (multiplicado por la cantidad)
    val basePriceQ: Int = priceQ,    //Precio unitario del producto (sin multiplicar la cantidad)
    val imageUrl: String? = null, //imagen del producto
    val quantity: Int = 1, //Numero de unidades del producto pedido

    //Atributos para la navegacion
    val serviceId: String = "", //Id del restaurante proveniente
    val serviceName: String = "",   //Nombre del restaurante
)
