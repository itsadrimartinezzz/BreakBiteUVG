package com.grupo6.breakbiteuvg.ui.ServiceScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class ServiceOrderTab { ORDERS, STORE }

data class ServiceOrderUi(
    val id: String,
    val estado: String,
    val cliente: String,
    val productosCount: Int,
    val totalQ: String
)

private val Green = Color(0xFF2E584A)
private val EmptyBg = Color(0xFFF3F4F6)
private val EmptyText = Color(0xFF6B7280)

@Composable
fun ServiceOrdersScreen(
    negocio: String,
    tag: String?,
    orders: List<ServiceOrderUi>,
    selectedTab: ServiceOrderTab,
    onTabChange: (ServiceOrderTab) -> Unit,
    onEditHeader: () -> Unit,
    onOpenOrder: (ServiceOrderUi) -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Green) {
                NavigationBarItem(
                    selected = selectedTab == ServiceOrderTab.ORDERS,
                    onClick = { onTabChange(ServiceOrderTab.ORDERS) },
                    icon = { Icon(Icons.Default.Menu, null) },
                    label = { Text("Pedidos", color = Color.White) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.7f),
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.White.copy(alpha = 0.7f),
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == ServiceOrderTab.STORE,
                    onClick = { onTabChange(ServiceOrderTab.STORE) },
                    icon = { Icon(Icons.Default.ShoppingCart, null) },
                    label = { Text("Tienda", color = Color.White) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.7f),
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.White.copy(alpha = 0.7f),
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding(), bottom = padding.calculateBottomPadding())
        ) {
            item {
                OrdersHeader(negocio = negocio, tag = tag, onEditHeader = onEditHeader)
            }

            item {
                Text(
                    text = "Pedidos actuales",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }

            if (orders.isEmpty()) {
                item {
                    EmptyOrdersBlock(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 8.dp)
                    )
                }
            } else {
                items(orders) { order ->
                    OrderRow(
                        order = order,
                        onOpen = { onOpenOrder(order) },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 12.dp)
                    )
                }
                // espacio para no tapar con la bottom bar
                item { Spacer(Modifier.height(80.dp)) }
            }
        }
    }
}

@Composable
private fun OrdersHeader(
    negocio: String,
    tag: String?,
    onEditHeader: () -> Unit
) {
    Box(Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(Green)
        )
        if (!tag.isNullOrBlank()) {
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = Color(0xFF1D3B31),
                tonalElevation = 0.dp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = 20.dp)
            ) {
                Text(
                    text = tag,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = negocio,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(Modifier.height(8.dp))
        OutlinedButton(
            onClick = onEditHeader,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Editar encabezado")
        }
    }
}

@Composable
private fun EmptyOrdersBlock(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = EmptyBg,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Aún no hay pedidos.\nCuando los usuarios envíen uno, aparecerá aquí.",
            color = EmptyText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun OrderRow(
    order: ServiceOrderUi,
    onOpen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(order.estado, color = Green)
                    Text(order.cliente, fontWeight = FontWeight.Bold)
                    Text("${order.productosCount} producto(s)")
                }
                Text(order.totalQ, fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = onOpen,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Green)
            ) { Text("Ver Pedido", color = Color.White) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewServiceOrders_Empty() {
    MaterialTheme {
        ServiceOrdersScreen(
            negocio = "Tú negocio",
            tag = "Tú Negocio",
            orders = emptyList(),
            selectedTab = ServiceOrderTab.ORDERS,
            onTabChange = {},
            onEditHeader = {},
            onOpenOrder = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewServiceOrders_WithOne() {
    MaterialTheme {
        val mock = listOf(
            ServiceOrderUi("BB-2001", "Pendiente", "Usuario", 3, "Q95")
        )
        ServiceOrdersScreen(
            negocio = "Tú negocio",
            tag = "Tú Negocio",
            orders = mock,
            selectedTab = ServiceOrderTab.ORDERS,
            onTabChange = {},
            onEditHeader = {},
            onOpenOrder = {}
        )
    }
}