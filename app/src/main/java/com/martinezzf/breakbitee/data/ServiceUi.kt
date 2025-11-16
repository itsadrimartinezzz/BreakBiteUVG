/**
 * Model que representa a los restaurantes en UserHomeScreen
 */

package com.martinezzf.breakbitee.data

import kotlinx.serialization.Serializable

@Serializable
data class ServiceUi(
    val id: String, //id del restaurante
    val name: String, //nombre del restaurante
    val imageUrl: String, //imagen de fondo del restaurante
    val bannerUrl: String, //Logo del restaurante
    val time: String //tiempo estimado de preparacion de productos del restaurante
)

