/**
 * Pantalla donde servicio puede ver los detalles de una orden y marcar la orden completado.
 */

package com.martinezzf.breakbitee.ui.serviceScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.martinezzf.breakbitee.data.UserOrderItemUi
import com.martinezzf.breakbitee.data.ServiceOrderUi

//Composable de ServiceOrderDetailScreen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceOrderDetailScreen(
    order: ServiceOrderUi,
    items: List<OrderItemUi>,
    onBack: () -> Unit,
    onComplete: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Detalles del ",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Bu",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2E584A)
                )
            )
        },
        bottomBar = {
            if (order.estado != "Completado") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = onComplete,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2E584A)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "Marcar como completado",
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 🔹 Encabezado con info del pedido
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF5F4F8))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(50)),
                        color = Color(0xFFD9D9D9)
                    ) {}

                    Spacer(Modifier.width(12.dp))

                    Column(Modifier.weight(1f)) {
                        Text(
                            text = order.estado,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF2E584A),
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = order.cliente,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "${order.cantidadProductos} producto(s)",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }

                    Text(
                        text = order.total,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // 🔹 Lista de productos
            items(items) { item ->
                OrderItemCard(item)
            }

            item { Spacer(Modifier.height(80.dp)) }
        }
    }
}

//Composable del OrderItemCard
@Composable
private fun OrderItemCard(item: OrderItemUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFE7EFEA)) // ✅ Fondo verde suave como en la imagen
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.imageUrl.ifEmpty { null },
            contentDescription = item.nombre,
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFD9D9D9)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.width(14.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.nombre,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Q${item.precio}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}
