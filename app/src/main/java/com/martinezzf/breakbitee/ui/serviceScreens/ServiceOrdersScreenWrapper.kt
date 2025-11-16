/**
 * Consiste en un composable intermediario que conecta el FakeApi, escuchar el tiempo real de pedidos del restaurante y pasar los pedidos a ServiceOrderScreen.
 */

package com.martinezzf.breakbitee.ui.serviceScreens

import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import com.martinezzf.breakbitee.data.FakeApi
import com.martinezzf.breakbitee.data.ServiceOrderUi
import com.martinezzf.breakbitee.ui.serviceScreens.ServiceTab

@Composable
fun ServiceOrdersScreenWrapper(
    restaurante: String,
    tag: String?,
    logoUrl: String,
    onOpenOrder: (ServiceOrderUi) -> Unit,
    onLogout: () -> Unit
) {
    //Obtiene los pedidos del restaurante en tiempo real
    val pedidosState = FakeApi.getPedidos(restaurante).collectAsState()

    //Extrae la lista actual del restaurante
    val orders = pedidosState.value

    //Controla la pestaña seleccionada (bottom bar)
    var selectedTab by remember { mutableStateOf(ServiceTab.ORDERS) }

    //Llama a la pantalla de ServiceOrdersScreen para pasar la informacion.
    ServiceOrdersScreen(
        negocio = restaurante,
        tag = tag,
        logoUrl = logoUrl,
        orders = orders,
        selectedTab = selectedTab,
        onTabChange = { selectedTab = it },
        onEditHeader = {},
        onOpenOrder = onOpenOrder,
        onLogout = onLogout
    )
}
