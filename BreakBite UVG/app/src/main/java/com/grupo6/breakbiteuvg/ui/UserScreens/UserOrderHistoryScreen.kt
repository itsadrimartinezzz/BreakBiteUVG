package com.grupo6.breakbiteuvg.ui.UserScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.res.painterResource
import com.grupo6.breakbiteuvg.R

private val BannerGreen = Color(0xFF2E584A)

data class UserOrderHistoryUi(
    val id: String,
    val serviceName: String,
    val productCount: Int,
    val total: String,
    val status: String,
    val dateLabel: String
)

@Composable
fun UserOrderHistoryScreen(
    orders: List<UserOrderHistoryUi>,
    onOpenOrderDetail: (UserOrderHistoryUi) -> Unit,
    selectedTab: UserTab,
    onTabChange: (UserTab) -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val logo = if (isDark) R.drawable.ic_bu_blanco else R.drawable.ic_bu_negro

    Scaffold(
        bottomBar = {
            BottomBarUser(selected = selectedTab, onTabChange = onTabChange)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Mis ",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp))
                Image(
                    painter = painterResource(id = logo),
                    contentDescription = "BU",
                    modifier = Modifier.height(38.dp)
                )
            }

            Spacer(Modifier.height(8.dp))
            
            orders.forEach { order ->
                OrderHistoryCard(
                    order = order,
                    onOpen = { onOpenOrderDetail(order) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Composable
private fun OrderHistoryCard(
    order: UserOrderHistoryUi,
    onOpen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 2.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // avatar placeholder
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE0E0E0))
                )
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(order.status, color = BannerGreen, fontWeight = FontWeight.Bold)
                    Text(order.serviceName, fontWeight = FontWeight.SemiBold)
                    Text("${order.productCount} producto(s)")
                    Text(order.dateLabel, fontSize = MaterialTheme.typography.bodySmall.fontSize)
                }
                Spacer(Modifier.width(8.dp))
                Text(order.total, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = onOpen,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BannerGreen)
            ) {
                Text("Ver Pedido", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserOrderHistoryScreen() {
    val mockOrders = listOf(
        UserOrderHistoryUi(
            id = "1",
            serviceName = "Servicio",
            productCount = 1,
            total = "Q1",
            status = "Entregado",
            dateLabel = "lun 01 de ene - 01:00 am"
        ),
        UserOrderHistoryUi(
            id = "2",
            serviceName = "Servicio",
            productCount = 2,
            total = "Q3",
            status = "Entregado",
            dateLabel = "mar 02 de ene - 02:00 pm"
        )
    )

    UserOrderHistoryScreen(
        orders = mockOrders,
        onOpenOrderDetail = {},
        selectedTab = UserTab.HISTORY,
        onTabChange = {}
    )
}