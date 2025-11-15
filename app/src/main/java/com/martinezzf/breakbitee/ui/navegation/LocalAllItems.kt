package com.martinezzf.breakbitee.ui.navegation

import androidx.compose.runtime.compositionLocalOf
import com.martinezzf.breakbitee.data.UserOrderItemUi

// Contexto compartido entre pantallas
val LocalAllItems = compositionLocalOf { mutableListOf<UserOrderItemUi>() }
