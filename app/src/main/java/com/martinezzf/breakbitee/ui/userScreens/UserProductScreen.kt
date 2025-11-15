package com.martinezzf.breakbitee.ui.userScreens

import androidx.compose.foundation.*
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.martinezzf.breakbitee.data.ProductDetailUi
import com.martinezzf.breakbitee.data.ProductParameterUi

@Composable
fun UserProductScreen(
    product: ProductDetailUi,
    onBack: () -> Unit,
    onAddToOrder: (ProductDetailUi) -> Unit
) {
    val BannerGreen = Color(0xFF2E584A)
    val LightGreen = Color(0xFF497766)
    val colors = MaterialTheme.colorScheme
    val isDark = isSystemInDarkTheme()

    var quantity by remember { mutableIntStateOf(1) }

    // Mapas para expandir secciones y selección de opciones
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

    // Cálculo del precio total dinámico
    val totalQ by remember {
        derivedStateOf {
            val extras = product.parameters
                .flatMap { it.options }
                .filter { selectedMap[it.id] == true }
                .sumOf { it.extra }
            (product.basePriceQ + extras) * quantity
        }
    }

    Scaffold(
        containerColor = colors.background,
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (isDark) LightGreen.copy(alpha = 0.2f) else LightGreen)
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Contador de cantidad
                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = if (isDark) BannerGreen.copy(alpha = 0.8f) else BannerGreen
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
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.clickable { if (quantity > 1) quantity-- }
                        )
                        Text("$quantity", color = Color.White)
                        Text(
                            text = "+",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.clickable { quantity++ }
                        )
                    }
                }

                Spacer(Modifier.width(12.dp))

                Button(
                    onClick = { onAddToOrder(product) },
                    modifier = Modifier
                        .height(46.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDark) LightGreen else BannerGreen,
                        contentColor = Color.White
                    )
                ) {
                    Text("Agregar a la orden", fontWeight = FontWeight.SemiBold)
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
                .background(colors.background)
        ) {
            // Imagen superior del producto
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 20.dp,
                                bottomEnd = 20.dp
                            )
                        )
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    colors.background.copy(alpha = 0.9f)
                                )
                            )
                        )
                )

                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopStart)
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
            }

            // Logo del restaurante
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
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(colors.surfaceVariant)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = product.serviceName,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isDark) LightGreen else BannerGreen
                )
            }

            // Información principal
            Column(Modifier.padding(horizontal = 16.dp)) {
                Text(
                    product.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = colors.onBackground
                )
                if (product.description.isNotBlank()) {
                    Spacer(Modifier.height(6.dp))
                    Text(
                        product.description,
                        color = colors.onSurfaceVariant
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    "Q$totalQ",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isDark) LightGreen else BannerGreen
                )
            }

            Spacer(Modifier.height(12.dp))

            // Parámetros
            product.parameters.forEach { param ->
                ParameterBlock(
                    parameter = param,
                    isExpanded = expandedMap[param.id] == true,
                    onToggleExpand = {
                        expandedMap[param.id] = !(expandedMap[param.id] ?: true)
                    },
                    selectedMap = selectedMap,
                    isDark = isDark,
                    BannerGreen = BannerGreen,
                    LightGreen = LightGreen,
                    colors = colors
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
    selectedMap: MutableMap<String, Boolean>,
    isDark: Boolean,
    BannerGreen: Color,
    LightGreen: Color,
    colors: ColorScheme
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
                color = if (isDark) LightGreen else BannerGreen,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = null,
                tint = if (isDark) LightGreen else BannerGreen
            )
        }

        if (isExpanded) {
            Spacer(Modifier.height(8.dp))
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = colors.surfaceVariant.copy(alpha = if (isDark) 0.4f else 0.2f)
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
                                onCheckedChange = { selectedMap[opt.id] = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = if (isDark) LightGreen else BannerGreen,
                                    uncheckedColor = colors.outline
                                )
                            )

                            val label = buildString {
                                append(opt.name)
                                if (opt.extra != 0) append(" (+Q${opt.extra})")
                            }

                            Text(
                                label,
                                color = colors.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}
