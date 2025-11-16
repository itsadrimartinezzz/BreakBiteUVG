/**
 * Se utiliza para compartir datos entre pantallas
 * Se utiliza la herramienta de compositionLocalOf para hacer un contexto global temporal como variables compartidas, estados globales, dependencias u objetos comunes.
 */

package com.martinezzf.breakbitee.ui.navegation

import androidx.compose.runtime.compositionLocalOf
import com.martinezzf.breakbitee.data.UserOrderItemUi

//Se comparte una lista, donde se encuentra todos los productos del pedido del usuario.
val LocalAllItems = compositionLocalOf { mutableListOf<UserOrderItemUi>() }
