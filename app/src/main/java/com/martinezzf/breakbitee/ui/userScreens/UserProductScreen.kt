/**
 * Pnatalla donde el usuario ve el detalle de un producto dentro del restaurante como la imagen, precio, descripcion.
 */

package com.martinezzf.breakbitee.ui.userScreens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.martinezzf.breakbitee.data.ProductDetailUi
import com.martinezzf.breakbitee.data.ProductParameterUi
import com.martinezzf.breakbitee.data.UserOrderItemUi

//Constructor para agregarlo al historial de pedidos.
private fun construirItemDePedido(
    product: ProductDetailUi,
    quantity: Int,
    selectedMap: Map<String, Boolean>
): UserOrderItemUi {

    val extras = product.parameters
        .flatMap { it.options }
        .filter { selectedMap[it.id] == true }
        .sumOf { it.extra }

    return UserOrderItemUi(
        id = product.id,
        name = product.name,
        priceQ = product.basePriceQ + extras,
        basePriceQ = product.basePriceQ,
        imageUrl = product.imageUrl,
        quantity = quantity,
        serviceName = product.serviceName,
        serviceId = product.serviceName,

        orderId = ""
    )
}

//Composable de la pantalla de informacion del producto, UserProductScreen.
@Composable
fun UserProductScreen(
    product: ProductDetailUi,
    onBack: () -> Unit,
    onAddToOrder: (UserOrderItemUi) -> Unit
) {
    val BannerGreen = Color(0xFF2E584A)
    val LightGreen = Color(0xFF497766)
    val colors = MaterialTheme.colorScheme
    val isDark = isSystemInDarkTheme()

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
                        val item = construirItemDePedido(product, quantity, selectedMap)
                        onAddToOrder(item)
                    },
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
        ) {

            // Imagen
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = product.serviceName,
                    color = BannerGreen,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Column(Modifier.padding(horizontal = 16.dp)) {

                Text(
                    product.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(product.description)

                Text(
                    "Q$totalQ",
                    style = MaterialTheme.typography.titleMedium,
                    color = BannerGreen
                )
            }

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
        }
    }
}

//Composable para mostrar paametros en un componente (expandir/colapsar, seleccionar opciones, ver extras)
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
                color = BannerGreen,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = null,
                tint = BannerGreen
            )
        }

        if (isExpanded) {
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
                        append(opt.name)
                        if (opt.extra != 0) append(" (+Q${opt.extra})")
                    }

                    Text(label)
                }
            }
        }
    }
}
