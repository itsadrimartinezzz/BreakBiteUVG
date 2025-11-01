package com.martinezzf.breakbitee.ui.userScreens


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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.martinezzf.breakbitee.data.ProductDetailUi
import com.martinezzf.breakbitee.data.ProductParameterUi

private val BannerGreen = Color(0xFF2E584A)
private val CardBg = Color(0xFFF2F4F5)

@Composable
fun UserProductScreen(
    product: ProductDetailUi,
    onBack: () -> Unit,
    onAddToOrder: (ProductDetailUi) -> Unit
) {
    var quantity by remember { mutableIntStateOf(1) }

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
            (product.basePriceQ + extras) * quantity
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
                    Row(
                        modifier = Modifier
                            .height(40.dp)
                            .width(90.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "-",
                            color = Color.White,
                            modifier = Modifier.clickable { if (quantity > 1) quantity-- }
                        )
                        Text("$quantity", color = Color.White)
                        Text(
                            text = "+",
                            color = Color.White,
                            modifier = Modifier.clickable { quantity++ }
                        )
                    }
                }

                Spacer(Modifier.width(12.dp))

                Button(
                    onClick = {
                        onAddToOrder(product)
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
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                )
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BannerGreen)
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 20.dp,
                                bottomEnd = 20.dp
                            )
                        )
                )

                IconButton(
                    onClick = onBack,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    model = when (product.serviceName) {
                        "Cafe Barista" -> "https://media.licdn.com/dms/image/v2/C4D0BAQG0iaY0mTFOtg/company-logo_200_200/company-logo_200_200/0/1676502742315/caf_barista_logo?e=2147483647&v=beta&t=RdzwCEyGJJeYckb8KiViPVjlcNdx3t6eEXkxJXe_9g0"
                        "& Cafe" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTHCi3JodBohfzg0Cr5tQ_Z9nlZuLo58XNDg&s"
                        "Gitane" -> "https://pedidosya.dhmedia.io/image/pedidosya/restaurants/cafe-gitane.png"
                        "Go Green" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4lqfr6uvS-Bm6YAaHyaUfXcqjEw2HSYB15g&s"
                        "Panitos y algo mas" -> "https://pedidosya.dhmedia.io/image/pedidosya/restaurants/panitos-y-algo-mas-logo.jpg"
                        "Mixtas Frankfurt" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSx57XNb3GJakIiMYtpxB19tr2V7BGVsdV8tQ&s"
                        "Golden Harvest" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHnFvfrTUk3L5N5eWP08HyG8BTGiAEqxaH7Q&s"
                        else -> ""
                    },
                    contentDescription = product.serviceName,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF1D3B31)),
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.width(8.dp))
                Text(
                    text = product.serviceName,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Column(Modifier.padding(horizontal = 16.dp)) {
                Text(
                    product.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                if (product.description.isNotBlank()) {
                    Spacer(Modifier.height(6.dp))
                    Text(product.description)
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    "Q$totalQ",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(12.dp))

            product.parameters.forEach { param ->
                ParameterBlock(
                    parameter = param,
                    isExpanded = expandedMap[param.id] == true,
                    onToggleExpand = {
                        expandedMap[param.id] = !(expandedMap[param.id] ?: true)
                    },
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
                                .clickable {
                                    selectedMap[opt.id] = !(selectedMap[opt.id] ?: false)
                                }
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
