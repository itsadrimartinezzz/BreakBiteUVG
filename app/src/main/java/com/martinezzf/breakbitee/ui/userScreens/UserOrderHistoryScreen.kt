package com.martinezzf.breakbitee.ui.userScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.martinezzf.breakbitee.data.OrderUi
import com.martinezzf.breakbitee.ui.navegation.UserTab

@Composable
fun UserOrderHistoryScreen(
    orders: List<OrderUi>,
    allItems: List<UserOrderItemUi>,
    onOpenOrderDetail: (OrderUi) -> Unit,
    selectedTab: UserTab,
    onTabChange: (UserTab) -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Scaffold(
        containerColor = colors.background,
        bottomBar = { BottomBarUser(selectedTab, onTabChange) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
                .padding(padding)
        ) {
            Spacer(Modifier.height(20.dp))

            Text(
                text = "Mis pedidos",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = colors.onBackground,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(16.dp))

            if (orders.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "No tienes pedidos aún",
                        color = colors.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(orders) { order ->
                        OrderCard(order, allItems, onOpenOrderDetail)
                    }
                }
            }
        }
    }
}

@Composable
private fun OrderCard(
    order: OrderUi,
    allItems: List<UserOrderItemUi>,
    onOpenOrderDetail: (OrderUi) -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val restaurantItems = allItems.filter { it.id.startsWith(order.serviceName) }

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = colors.surface,
        tonalElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = when (order.serviceName) {
                        "Cafe Barista" -> "https://media.licdn.com/dms/image/v2/C4D0BAQG0iaY0mTFOtg/company-logo_200_200/company-logo_200_200/0/1676502742315/caf_barista_logo?e=2147483647&v=beta&t=RdzwCEyGJJeYckb8KiViPVjlcNdx3t6eEXkxJXe_9g0"
                        "& Cafe" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTHCi3JodBohfzg0Cr5tQ_Z9nlZuLo58XNDg&s"
                        "Gitane" -> "https://pedidosya.dhmedia.io/image/pedidosya/restaurants/cafe-gitane.png"
                        "Go Green" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4lqfr6uvS-Bm6YAaHyaUfXcqjEw2HSYB15g&s"
                        "Panitos y algo mas" -> "https://pedidosya.dhmedia.io/image/pedidosya/restaurants/panitos-y-algo-mas-logo.jpg"
                        "Mixtas Frankfurt" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSx57XNb3GJakIiMYtpxB19tr2V7BGVsdV8tQ&s"
                        "Golden Harvest" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHnFvfrTUk3L5N5eWP08HyG8BTGiAEqxaH7Q&s"
                        else -> "https://cdn-icons-png.flaticon.com/512/857/857681.png"
                    },
                    contentDescription = order.serviceName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(colors.surfaceVariant)
                )

                Spacer(Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        order.status,
                        color = colors.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(order.serviceName, fontWeight = FontWeight.SemiBold, color = colors.onSurface)
                    Text("${order.productCount} producto(s)", fontSize = 13.sp, color = colors.onSurfaceVariant)
                    Text(order.dateTime, fontSize = 13.sp, color = colors.onSurfaceVariant)
                }

                Text(
                    "Q${order.totalQ}",
                    fontWeight = FontWeight.Bold,
                    color = colors.onSurface
                )
            }

            Spacer(Modifier.height(10.dp))

            if (restaurantItems.isNotEmpty()) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    restaurantItems.groupBy { it.name }.forEach { (nombre, lista) ->
                        val cantidad = lista.size
                        val producto = lista.first()

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                AsyncImage(
                                    model = producto.imageUrl,
                                    contentDescription = producto.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(35.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(colors.surfaceVariant)
                                )
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    "$nombre ×$cantidad",
                                    fontWeight = FontWeight.Medium,
                                    color = colors.onSurface
                                )
                            }

                            Text(
                                text = "Q${producto.priceQ * cantidad}",
                                color = colors.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Divider(color = colors.outlineVariant, modifier = Modifier.padding(vertical = 4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total", fontWeight = FontWeight.Bold, color = colors.onSurface)
                        Text(
                            text = "Q${restaurantItems.sumOf { it.priceQ }}",
                            fontWeight = FontWeight.Bold,
                            color = colors.primary
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = { onOpenOrderDetail(order) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary,
                    contentColor = colors.onPrimary
                )
            ) {
                Text("Ver Pedido", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
