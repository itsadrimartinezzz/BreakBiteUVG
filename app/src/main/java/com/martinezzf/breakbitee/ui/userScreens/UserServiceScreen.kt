package com.martinezzf.breakbitee.ui.userScreens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

private val BannerGreen = Color(0xFF2E584A)
private val CardBg = Color(0xFFF2F4F5)
private val ItemBg = Color(0xFFE7EFEA)

data class SimpleProductUi(
    val id: String,
    val name: String,
    val priceLabel: String,
    var imageUrl: String = "",
    var descripcion: String = ""
)

data class SimpleCategoryUi(
    val id: String,
    val name: String,
    val products: List<SimpleProductUi>
)

@Composable
fun UserServiceScreen(
    serviceName: String,
    popular: List<SimpleProductUi>,
    categories: List<SimpleCategoryUi>,
    onBack: () -> Unit,
    onOpenProduct: (SimpleProductUi) -> Unit,
    onCompleteOrder: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onCompleteOrder,
                    modifier = Modifier
                        .height(46.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D3B31))
                ) {
                    Text("Completar pedido", color = Color.White)
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                )
        ) {
            item { Header(serviceName = serviceName, onBack = onBack) }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .offset(y = (-18).dp),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        shape = RoundedCornerShape(24.dp),
                        color = Color(0xFF1D3B31)
                    ) {
                        Text(
                            text = "Retiro en local",
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            item { Divider(thickness = 1.dp, color = Color(0xFFE0E0E0)) }

            if (popular.isNotEmpty()) {
                item { SectionTitle("Productos populares") }
                item {
                    PopularGrid(
                        products = popular,
                        onOpen = onOpenProduct,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            items(categories) { cat ->
                SectionTitle(cat.name)
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    cat.products.forEach { p ->
                        ProductRowCard(product = p, onOpen = { onOpenProduct(p) })
                    }
                }
                Spacer(Modifier.height(8.dp))
                Divider(thickness = 1.dp, color = Color(0xFFE0E0E0))
            }

            item { Spacer(Modifier.height(8.dp)) }
        }
    }
}

@Composable
private fun Header(serviceName: String, onBack: () -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(BannerGreen)
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
            }
            Text(
                text = serviceName,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp, end = 16.dp)
            )
        }
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 10.dp)
    )
}

@Composable
private fun PopularGrid(
    products: List<SimpleProductUi>,
    onOpen: (SimpleProductUi) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 120.dp, max = 280.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(products) { p ->
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(ItemBg)
                    .clickable { onOpen(p) }
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = p.imageUrl,
                    contentDescription = p.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(Modifier.height(6.dp))
                Text(p.name, maxLines = 1)
                Text(p.priceLabel, color = BannerGreen)
            }
        }
    }
}

@Composable
private fun ProductRowCard(
    product: SimpleProductUi,
    onOpen: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = CardBg,
        tonalElevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpen() }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFD0D9D4))
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(product.name, fontWeight = FontWeight.SemiBold)
                Text(product.priceLabel, color = BannerGreen)
            }
        }
    }
}
