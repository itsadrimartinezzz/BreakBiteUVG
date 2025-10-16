package com.grupo6.breakbiteuvg.ui.UserScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val BannerGreen = Color(0xFF2E584A)
private val CardBg      = Color(0xFFF2F4F5)

data class ProductOptionUi(
    val id: String,
    val label: String,
    val priceDeltaQ: Int = 0,
    val initiallySelected: Boolean = false
)

data class ProductParameterUi(
    val id: String,
    val name: String,
    val options: List<ProductOptionUi>
)

data class ProductDetailUi(
    val id: String,
    val name: String,
    val description: String,
    val basePriceQ: Int,
    val parameters: List<ProductParameterUi>,
    val serviceName: String = "Servicio"
)

@Composable
fun UserProductScreen(
    product: ProductDetailUi,
    onBack: () -> Unit,
    onAddToOrder: (ProductDetailUi) -> Unit
) {
    var quantity by remember { mutableStateOf(1) }

    val expandedMap = remember(product.id) {
        mutableStateMapOf<String, Boolean>().apply {
            product.parameters.forEach { put(it.id, true) }
        }
    }
    val selectedMap = remember(product.id) {
        mutableStateMapOf<String, Boolean>().apply {
            product.parameters.flatMap { it.options }.forEach { option ->
                put(option.id, option.initiallySelected)
            }
        }
    }

    val totalQ by remember {
        derivedStateOf {
            val extras = product.parameters
                .flatMap { it.options }
                .filter { selectedMap[it.id] == true }
                .sumOf { it.priceDeltaQ }
            product.basePriceQ + extras
        }
    }

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = Color.Black
                ) {
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(56.dp)
                            .clickable { quantity = (quantity % 9) + 1 }, // 1..9 demo
                        contentAlignment = Alignment.Center
                    ) {
                        Text("- $quantity +", color = Color.White)
                    }
                }
                Spacer(Modifier.width(12.dp))
                Button(
                    onClick = {
                        onAddToOrder(product)
                        onBack()
                    },
                    modifier = Modifier
                        .height(46.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BannerGreen)
                ) {
                    Text("Agregar a la orden", color = Color.White)
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
                    .background(BannerGreen)
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.padding(4.dp)
                ) { Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White) }

                // Imagen del producto (placeholder)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp, top = 40.dp, bottom = 12.dp)
                        .height(160.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color(0xFFD0D9D4))
                        .align(Alignment.BottomCenter)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF1D3B31))
                )
                Spacer(Modifier.width(8.dp))
                Text(product.serviceName, fontWeight = FontWeight.SemiBold)
            }

            Column(Modifier.padding(horizontal = 16.dp)) {
                Text(product.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                if (product.description.isNotBlank()) {
                    Spacer(Modifier.height(6.dp))
                    Text(product.description)
                }
                Spacer(Modifier.height(8.dp))
                Text("Q$totalQ", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(12.dp))

            product.parameters.forEach { param ->
                ParameterBlock(
                    parameter = param,
                    isExpanded = expandedMap[param.id] == true,
                    onToggleExpand = { expandedMap[param.id] = !(expandedMap[param.id] ?: true) },
                    selectedMap = selectedMap
                )
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}


@Composable
private fun ParameterBlock(
    parameter: ProductParameterUi,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    selectedMap: MutableMap<String, Boolean>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggleExpand() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = parameter.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = null
            )
        }

        if (isExpanded) {
            Spacer(Modifier.height(8.dp))
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = CardBg
            ) {
                Column(Modifier.padding(vertical = 6.dp)) {
                    parameter.options.forEach { opt ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedMap[opt.id] = !(selectedMap[opt.id] ?: false) }
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = selectedMap[opt.id] == true,
                                onCheckedChange = { selectedMap[opt.id] = it }
                            )
                            val label = buildString {
                                append(opt.label)
                                if (opt.priceDeltaQ != 0) append(" (+Q${opt.priceDeltaQ})")
                            }
                            Text(label)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewUserProductScreen() {
    val sample = ProductDetailUi(
        id = "item-1",
        name = "Item",
        description = "Esta es la descripción del objeto",
        basePriceQ = 1,
        parameters = listOf(
            ProductParameterUi(
                id = "param1",
                name = "Parámetro",
                options = listOf(
                    ProductOptionUi(id = "opt1", label = "Opción 1"),
                    ProductOptionUi(id = "opt2", label = "Opción 2")
                )
            )
        ),
        serviceName = "Servicio"
    )

    UserProductScreen(
        product = sample,
        onBack = {},
        onAddToOrder = {}
    )
}
