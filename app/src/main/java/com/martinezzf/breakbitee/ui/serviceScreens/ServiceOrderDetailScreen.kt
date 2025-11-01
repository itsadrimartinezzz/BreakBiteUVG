package com.martinezzf.breakbitee.ui.serviceScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceOrderDetailScreen(
    customerName: String,
    statusText: String,
    items: List<OrderItemUi>,
    onBack: () -> Unit,
    onComplete: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del pedido") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2E584A),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    Text(
                        "Cliente: $customerName",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Estado: $statusText",
                        style = MaterialTheme.typography.bodyMedium,
                        color = when (statusText) {
                            "Pendiente" -> Color(0xFFFF9800)
                            "En preparación" -> Color(0xFF2196F3)
                            "Completado" -> Color(0xFF4CAF50)
                            else -> MaterialTheme.colorScheme.onSurface
                        }
                    )
                }
            }

            item {
                Text(
                    "Productos",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            items(items) { item ->
                OrderItemCard(item)
            }

            if (statusText != "Completado") {
                item {
                    Button(
                        onClick = onComplete,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        )
                    ) {
                        Text("Marcar como completado")
                    }
                }
            }

            item { Spacer(Modifier.height(80.dp)) }
        }
    }
}

@Composable
private fun OrderItemCard(item: OrderItemUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFE8F0F2))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.imageUrl.ifEmpty { null },
            contentDescription = item.nombre,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFCFD8DC)),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.width(12.dp))

        Column(Modifier.weight(1f)) {
            Text(
                item.nombre,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                "Q${item.precio}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF2E584A)
            )
        }

        Surface(
            shape = RoundedCornerShape(20.dp),
            color = if (item.listo) Color(0xFF4CAF50) else Color(0xFFFF9800),
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                if (item.listo) "✓" else "⏱",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                color = Color.White
            )
        }
    }
}