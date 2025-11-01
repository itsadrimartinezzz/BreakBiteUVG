package com.martinezzf.breakbitee.ui.userScreens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale

private val BannerGreen = Color(0xFF2E584A)
private val CardBg = Color(0xFFF2F4F5)
private val ItemBg = Color(0xFFE7EFEA)

data class UserOrderItemUi(
    val id: String,
    val name: String,
    val priceQ: Int,
    val imageUrl: String? = null,
    val quantity: Int = 1
)

data class UserOrderDetailUi(
    val id: String,
    val serviceName: String,
    val status: String,
    val items: List<UserOrderItemUi>,
    val serviceLogoUrl: String? = null
) {
    val totalQ: Int get() = items.sumOf { it.priceQ * it.quantity }
}

@Composable
fun UserOrderDetailScreen(
    data: UserOrderDetailUi,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .background(BannerGreen),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Detalle del pedido",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
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
                    Spacer(Modifier.width(8.dp))
                    Text("Volver", color = Color.White, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding(), bottom = padding.calculateBottomPadding())
        ) {
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
                    AsyncImage(
                        model = data.serviceLogoUrl,
                        contentDescription = data.serviceName,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFE1E5E7)),
                        contentScale = ContentScale.Crop
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

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (data.items.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No hay productos en este pedido", color = Color.Gray)
                    }
                } else {
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

                                AsyncImage(
                                    model = item.imageUrl,
                                    contentDescription = item.name,
                                    modifier = Modifier
                                        .size(56.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0xFFD0D9D4)),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(Modifier.width(12.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(
                                        text = "${item.name} ×${item.quantity}",
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}


