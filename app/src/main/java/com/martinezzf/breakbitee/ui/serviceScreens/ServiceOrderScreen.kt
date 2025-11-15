package com.martinezzf.breakbitee.ui.serviceScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.martinezzf.breakbitee.data.ServiceOrderUi

// Enum para las pestañas del restaurante
enum class ServiceOrderTab {
    ORDERS, STORE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceOrdersScreen(
    negocio: String,
    tag: String?,
    logoUrl: String,
    orders: List<ServiceOrderUi>,
    selectedTab: ServiceOrderTab,
    onTabChange: (ServiceOrderTab) -> Unit,
    onEditHeader: () -> Unit,
    onOpenOrder: (ServiceOrderUi) -> Unit,
    onLogout: () -> Unit
) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pedidos") },
                actions = {
                    IconButton(onClick = { showLogoutDialog = true }) {
                        Icon(
                            Icons.Default.ExitToApp,
                            contentDescription = "Cerrar sesión",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2E584A),
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomBarService(
                selected = selectedTab,
                onTabChange = onTabChange
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ServiceHeaderWithLogo(
                    negocio = negocio,
                    tag = tag,
                    logoUrl = logoUrl,
                    onEdit = onEditHeader
                )
            }

            item {
                Text(
                    "Pedidos actuales",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            if (orders.isEmpty()) {
                item {
                    EmptyOrdersBlock(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            } else {
                items(orders) { order ->
                    OrderCard(
                        order = order,
                        onClick = { onOpenOrder(order) },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            item { Spacer(Modifier.height(80.dp)) }
        }
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            icon = {
                Icon(
                    Icons.Default.ExitToApp,
                    contentDescription = null,
                    tint = Color(0xFF2E584A)
                )
            },
            title = { Text("Cerrar sesión") },
            text = { Text("¿Estás seguro que deseas cerrar sesión?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        onLogout()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color(0xFF2E584A)
                    )
                ) {
                    Text("Cerrar sesión")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
private fun ServiceHeaderWithLogo(
    negocio: String,
    tag: String?,
    logoUrl: String,
    onEdit: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(Color(0xFF2E584A))
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    AsyncImage(
                        model = logoUrl.ifEmpty { null },
                        contentDescription = "Logo $negocio",
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF1D3B31)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(Modifier.width(12.dp))

                    Text(
                        text = negocio,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = Color.White
                    )
                }

                if (!tag.isNullOrBlank()) {
                    Surface(
                        tonalElevation = 0.dp,
                        shape = RoundedCornerShape(24.dp),
                        color = Color(0xFF1D3B31),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = 18.dp)
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
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                        RoundedCornerShape(16.dp)
                    )
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(12.dp)
            ) {
                OutlinedButton(
                    onClick = onEdit,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Editar encabezado")
                }
            }
        }
    }
}

@Composable
private fun OrderCard(
    order: ServiceOrderUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF5F5F5))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(
            order.estado,
            style = MaterialTheme.typography.labelMedium,
            color = when (order.estado) {
                "Pendiente" -> Color(0xFFFF9800)
                "En preparación" -> Color(0xFF2196F3)
                "Completado" -> Color(0xFF4CAF50)
                else -> MaterialTheme.colorScheme.onSurface
            },
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.height(4.dp))

        Text(
            order.cliente,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(4.dp))

        Text(
            "${order.cantidadProductos} producto(s)",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E584A)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Ver Pedido")
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                order.total,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E584A)
            )
        }
    }
}

@Composable
private fun EmptyOrdersBlock(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF3F4F6))
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Default.ShoppingCart,
                contentDescription = null,
                tint = Color(0xFF6B7280),
                modifier = Modifier.size(48.dp)
            )
            Spacer(
                Modifier.height(8.dp)
            )
            Text(
                "No hay pedidos actuales",
                color = Color(0xFF6B7280),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun BottomBarService(
    selected: ServiceOrderTab,
    onTabChange: (ServiceOrderTab) -> Unit
) {
    NavigationBar(containerColor = Color(0xFF2E584A)) {
        NavigationBarItem(
            selected = selected == ServiceOrderTab.ORDERS,
            onClick = { onTabChange(ServiceOrderTab.ORDERS) },
            icon = { Icon(Icons.Default.Menu, null) },
            label = { Text("Pedidos") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White.copy(alpha = 0.6f),
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = selected == ServiceOrderTab.STORE,
            onClick = { onTabChange(ServiceOrderTab.STORE) },
            icon = { Icon(Icons.Default.ShoppingCart, null) },
            label = { Text("Tienda") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White.copy(alpha = 0.6f),
                indicatorColor = Color.Transparent
            )
        )
    }
}
