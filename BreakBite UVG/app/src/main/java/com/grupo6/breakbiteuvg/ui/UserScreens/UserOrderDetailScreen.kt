package com.grupo6.breakbiteuvg.ui.UserScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grupo6.breakbiteuvg.R

private val BannerGreen = Color(0xFF2E584A)
private val CardBg = Color(0xFFF2F4F5)
private val ItemBg = Color(0xFFE7EFEA)

data class UserOrderItemUi(
    val id: String,
    val name: String,
    val priceQ: Int
)

data class UserOrderDetailUi(
    val id: String,
    val serviceName: String,
    val status: String,
    val items: List<UserOrderItemUi>
) {
    val totalQ: Int get() = items.sumOf { it.priceQ }
}

@Composable
fun UserOrderDetailScreen(
    data: UserOrderDetailUi,
    onBack: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onBack,
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BannerGreen)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null, tint = Color.White)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding(), bottom = padding.calculateBottomPadding())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .background(BannerGreen)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mi",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_bu_blanco),
                        contentDescription = "BU",
                        modifier = Modifier.height(28.dp)
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = CardBg,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // avatar placeholder
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFE1E5E7))
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(data.status, color = BannerGreen, fontWeight = FontWeight.SemiBold)
                        Text(data.serviceName, fontWeight = FontWeight.Bold)
                        Text("${data.items.size} producto(s)")
                    }
                    Text("Q${data.totalQ}", fontWeight = FontWeight.SemiBold)

                }
            }

            // Lista de items
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                data.items.forEach { item ->
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = ItemBg,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // imagen placeholder
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFD0D9D4))
                            )
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f)) {
                                Text(text = item.name, fontWeight = FontWeight.SemiBold)
                                Text("Q${item.priceQ}", color = BannerGreen)
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewUserOrderDetailScreen() {
    val sample = UserOrderDetailUi(
        id = "ord-1",
        serviceName = "Servicio",
        status = "Pendiente",
        items = listOf(
            UserOrderItemUi("1", "Producto 1", 5),
            UserOrderItemUi("2", "Producto 2", 3),
            UserOrderItemUi("3", "Producto 3", 1)
        )
    )
    UserOrderDetailScreen(
        data = sample,
        onBack = {}
    )
}