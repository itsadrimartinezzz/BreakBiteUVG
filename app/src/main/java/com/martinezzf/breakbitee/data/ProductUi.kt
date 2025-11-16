/**
 * Define 3 data class que describen un producto mas detallados.
 */

package com.martinezzf.breakbitee.data

import kotlinx.serialization.Serializable

//Representa una opcion dentro de un parametro
@Serializable
data class ProductOptionUi(
    val id: String, //Id unico de la opcion
    val name: String, //Nombre visible del producto
    val extra: Int = 0, //Costo adicional
    val initiallySelected: Boolean = false //Preseleccion
)

//Representa un grupo de opciones
@Serializable
data class ProductParameterUi(
    val id: String, //id del parametro
    val name: String, //Nombre del tamaño (normal o deluxe)
    val options: List<ProductOptionUi> //Lista de opciones seleccionables
)

//Producto completo con la informacion y opciones de personalizacion
@Serializable
data class ProductDetailUi(
    val id: String, //id del producto
    val name: String, //nombre del producto
    val description: String, //descripcion del producto
    val basePriceQ: Int,   //precio base del producto
    val imageUrl: String = "", //imagen del producto
    val parameters: List<ProductParameterUi> = emptyList(), //lista de parametros
    val serviceName: String = "Servicio" //Restaurante al que pertenece.
)
