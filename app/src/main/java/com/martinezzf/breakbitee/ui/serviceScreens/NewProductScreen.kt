package com.martinezzf.breakbitee.ui.serviceScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewProductScreen(
    onBack: () -> Unit
) {
    var productName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Producto") },
                navigationIcon = {

                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2E584A),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Surface(
                        shape = RoundedCornerShape(24.dp),
                        color = Color(0xFF1D3B31)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Text("-", color = Color.White, style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.width(16.dp))
                            Text("1", color = Color.White, style = MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.width(16.dp))
                            Text("+", color = Color.White, style = MaterialTheme.typography.titleMedium)
                        }
                    }

                    // Add button
                    Button(
                        onClick = {  },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2E584A)
                        ),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text("Agregar a la orden")
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Image placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF2E584A)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text("Agregar imagen", color = Color.White)
                }
            }

            // Local chip
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFE0E0E0)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF1D3B31))
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Local", style = MaterialTheme.typography.bodyMedium)
                }
            }

            // Product name
            EditableField(
                label = "Nombre del Producto",
                value = productName,
                onValueChange = { productName = it },
                placeholder = "Ingresa el nombre"
            )

            // Description
            EditableField(
                label = "agregar descripción",
                value = description,
                onValueChange = { description = it },
                placeholder = "Ingresa la descripción"
            )

            // Price
            EditableField(
                label = "Precio",
                value = price,
                onValueChange = { price = it },
                placeholder = "Q 0.00"
            )

            // Add category
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Agregar categoría",
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(onClick = { /* Add category */ }) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar categoría")
                }
            }

            Spacer(Modifier.height(80.dp))
        }
    }
}

@Composable
private fun EditableField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                label,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            if (value.isEmpty()) {
                Text(
                    placeholder,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            } else {
                Text(
                    value,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        IconButton(onClick = { /* Edit field */ }) {
            Icon(Icons.Default.Edit, contentDescription = "Editar")
        }
    }
}