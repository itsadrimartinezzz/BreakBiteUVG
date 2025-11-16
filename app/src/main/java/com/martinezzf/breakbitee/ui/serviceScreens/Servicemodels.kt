/**
 * Define los modelos que se utiliza del lado del servicio.
 */

package com.martinezzf.breakbitee.ui.serviceScreens

import androidx.compose.ui.graphics.Color


//Representa los datos visuales del titulo de la tienda
data class ServiceHeaderUi(
    val name: String? = null, //nombre del servicio
    val tag: String? = null, //etiqueta del servicio
    val bannerColor: Color = Color(0xFF2E584A), //color del banner
    val avatarColor: Color = Color(0xFF1D3B31), //color del fondo del logo
    val logoUrl: String = "" //logo utilizando coil
)

//Producto dentro de un pedido visto desde la tienda
data class OrderItemUi(
    val id: String, //id del producto
    val nombre: String, //nombre del producto
    val precio: Int, //precio del producto
    val listo: Boolean, //estado listo o no del producto
    val imageUrl: String = "" //imagen del producto utilizando coil
)

//Representa un prodcuto mostrado en la tienda de servicio
data class ProductUi(
    val id: String, //id del producto
    val title: String, //Titulo
    val price: String, //Precio del producto
    val imageUrl: String = "" //Imagen del producto
)

//Representa una categoria del menu de restaurantes
data class CategoryUi(
    val id: String, //id de la categoria
    val name: String, //nombre de la categoria
    val products: List<ProductUi> //productos, recibe como parametro la lista ProductUi.
)

//Controlador de bottom bar de servicio
enum class ServiceTab {
    ORDERS, //Pestaña de orden
    STORE //Pestañaa de agregar producto en tienda
}