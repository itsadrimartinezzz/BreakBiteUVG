package com.martinezzf.breakbitee.ui.serviceScreens

import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import com.martinezzf.breakbitee.data.FakeApi
import com.martinezzf.breakbitee.data.ServiceOrderUi

@Composable
fun ServiceOrdersScreenWrapper(
    restaurante: String,
    tag: String?,
    logoUrl: String,
    onOpenOrder: (ServiceOrderUi) -> Unit,
    onLogout: () -> Unit
) {
    val pedidosState = FakeApi.getPedidos(restaurante).collectAsState()

    // Pedidos para este restaurante
    val orders = pedidosState.value

    // Tab default
    var selectedTab by remember { mutableStateOf(ServiceOrderTab.ORDERS) }

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
