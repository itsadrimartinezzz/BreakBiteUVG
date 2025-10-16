package com.grupo6.breakbiteuvg.ui.ServiceScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Green = Color(0xFF2E584A)

@Composable
fun NewProductScreen(
    onBack: () -> Unit,
    onEditHeaderImage: () -> Unit = {},
    onEditName: () -> Unit = {},
    onEditDescription: () -> Unit = {},
    onEditPrice: () -> Unit = {},
    onAddCategory: () -> Unit = {},
    onChangeQuantity: () -> Unit = {},
    onAddToOrder: () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            BottomActionBar(
                onChangeQuantity = onChangeQuantity,
                onAddToOrder = onAddToOrder
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(Green.copy(alpha = 0.9f)),
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                }

                // Placeholder agregar imagen
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(
                            Green.copy(alpha = 0.7f),
                            RoundedCornerShape(24.dp)
                        )
                        .clickable { onEditHeaderImage() },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Filled.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
                    Spacer(Modifier.height(6.dp))
                    Text("Agregar imagen", color = Color.White)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(Color(0xFF1D3B31), RoundedCornerShape(6.dp)) // pfp cuadrada placeholder
                )
                Spacer(Modifier.width(8.dp))
                Text("Local", fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Nombre del Producto",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onEditName) {
                    Icon(Icons.Filled.Edit, contentDescription = "Editar nombre")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "agregar descripción",
                    color = Color(0xFF6B6B6B),
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onEditDescription) {
                    Icon(Icons.Filled.Edit, contentDescription = "Editar descripción")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Precio",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onEditPrice) {
                    Icon(Icons.Filled.Edit, contentDescription = "Editar precio")
                }
            }

            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Agregar categoría",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onAddCategory) {
                    Icon(Icons.Filled.Add, contentDescription = "Agregar categoría")
                }
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun BottomActionBar(
    onChangeQuantity: () -> Unit,
    onAddToOrder: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // “chip” de cantidad (placeholder)
        Surface(
            shape = RoundedCornerShape(999.dp),
            color = Color.Black,
            tonalElevation = 0.dp
        ) {
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .widthIn(min = 56.dp)
                    .clickable { onChangeQuantity() },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "- 1 +", color = Color.White)
            }
        }

        Spacer(Modifier.width(12.dp))
        Button(
            onClick = onAddToOrder,
            modifier = Modifier
                .height(44.dp)
                .weight(1f),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green)
        ) {
            Text("Agregar a la orden", color = Color.White, textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewProductScreen() {
    NewProductScreen(onBack = {})
}