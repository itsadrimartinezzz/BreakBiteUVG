package com.grupo6.breakbiteuvg.ui.ServiceScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.grupo6.breakbiteuvg.R

private val Green = Color(0xFF2E584A)
private val CardBg = Color(0xFFF2F4F5)
private val ItemBg = Color(0xFFD5E3DD)

data class OrderItemUi(
    val id: String,
    val name: String,
    val priceQ: Int,
    val hasPhoto: Boolean = false
)

@Composable
fun ServiceOrderDetailScreen(
    customerName: String,
    statusText: String,
    items: List<OrderItemUi>,
    onBack: () -> Unit,
    onComplete: () -> Unit
) {
    val totalQ = items.sumOf { it.priceQ }
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onComplete,
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Green)
                ) {
                    Text("Marcar como completado", color = Color.White)
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding(), bottom = padding.calculateBottomPadding())
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(Green)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                        }
                        Spacer(Modifier.width(4.dp))
                        Text("Detalles del", color = Color.White, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.width(6.dp))
                        Image(
                            painter = painterResource(R.drawable.ic_bu_blanco),
                            contentDescription = "BU",
                            modifier = Modifier.height(22.dp)
                        )
                    }
                }
            }

            item {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = CardBg,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFE1E5E7))
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text(statusText, color = Green)
                            Text(customerName, fontWeight = FontWeight.Bold)
                            Text("${items.size} producto(s)")
                        }
                        Text("Q$totalQ", fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            items(items, key = { it.id }) { item ->
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = ItemBg,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val photoModifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(12.dp))
                        if (item.hasPhoto) {
                            Box(photoModifier.background(Color(0xFFB6C8C0)))
                        } else {
                            Box(photoModifier.background(Color(0xFFC7D6D0)))
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text(item.name, fontWeight = FontWeight.SemiBold)
                            Text("Q${item.priceQ}")
                        }
                    }
                }
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewServiceOrderDetailScreen() {
    val mockItems = listOf(
        OrderItemUi(id = "1", name = "Producto 1", priceQ = 5, hasPhoto = false),
        OrderItemUi(id = "2", name = "Producto 2", priceQ = 3, hasPhoto = false),
        OrderItemUi(id = "3", name = "Producto 3", priceQ = 1, hasPhoto = false)
    )
    MaterialTheme {
        ServiceOrderDetailScreen(
            customerName = "Usuario",
            statusText = "Pendiente",
            items = mockItems,
            onBack = {},
            onComplete = {}
        )
    }
}