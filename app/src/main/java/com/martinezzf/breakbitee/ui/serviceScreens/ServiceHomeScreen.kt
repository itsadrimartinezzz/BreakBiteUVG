package com.martinezzf.breakbitee.ui.serviceScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceHomeScreen(
    header: ServiceHeaderUi,
    popular: List<ProductUi>,
    categories: List<CategoryUi>,
    selectedTab: ServiceTab,
    onTabChange: (ServiceTab) -> Unit,
    onAddCategory: () -> Unit,
    onAddPopularProduct: () -> Unit,
    onOpenProduct: (ProductUi) -> Unit,
    onEditHeader: () -> Unit,
    onLogout: () -> Unit
) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tienda") },
                actions = {
                    IconButton(onClick = { showLogoutDialog = true }) {
                        Icon(
                            Icons.Default.ExitToApp,
                            contentDescription = "Cerrar sesión",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2E584A),
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        bottomBar = { BottomBar2(selectedTab, onTabChange) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding(), bottom = padding.calculateBottomPadding()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ServiceHeaderFullWidth(
                    header = header,
                    onEdit = onEditHeader
                )
            }

            item {
                Column(Modifier.padding(horizontal = 16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Añadir categoría", style = MaterialTheme.typography.titleMedium)
                        IconButton(onClick = onAddCategory) { Icon(Icons.Default.Add, null) }
                    }
                }
            }

            item {
                Column(Modifier.padding(horizontal = 16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Productos populares", style = MaterialTheme.typography.titleMedium)
                        IconButton(onClick = onAddPopularProduct) { Icon(Icons.Default.Add, null) }
                    }
                }
            }

            if (popular.isEmpty()) {
                item { EmptyBlock("Aún no hay productos populares.\nAgrega algunos para destacarlos.", Modifier.padding(horizontal = 16.dp)) }
            } else {
                item { PopularGrid(products = popular, onOpen = onOpenProduct, modifier = Modifier.padding(horizontal = 16.dp)) }
            }

            if (categories.isEmpty()) {
                item {
                    Column(Modifier.padding(horizontal = 16.dp)) {
                        Text("Categorías", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        EmptyBlock("No tienes categorías.\nCrea la primera para empezar.")
                    }
                }
            } else {
                items(categories.size) { i ->
                    CategorySection(categories[i], onOpenProduct, modifier = Modifier.padding(horizontal = 16.dp))
                    Spacer(Modifier.height(8.dp))
                }
                item { Spacer(Modifier.height(80.dp)) }
            }
        }
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            icon = {
                Icon(
                    Icons.Default.ExitToApp,
                    contentDescription = null,
                    tint = Color(0xFF2E584A)
                )
            },
            title = { Text("Cerrar sesión") },
            text = { Text("¿Estás seguro que deseas cerrar sesión?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        onLogout()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color(0xFF2E584A)
                    )
                ) {
                    Text("Cerrar sesión")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
private fun ServiceHeaderFullWidth(
    header: ServiceHeaderUi,
    onEdit: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(header.bannerColor)
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    AsyncImage(
                        model = header.logoUrl.ifEmpty { null },
                        contentDescription = "Logo ${header.name}",
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(header.avatarColor),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.align(Alignment.Top)
                    ) {
                        Text(
                            text = header.name ?: "Nombre del servicio",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = Color.White
                        )
                    }
                }

                if (!header.tag.isNullOrBlank()) {
                    Surface(
                        tonalElevation = 0.dp,
                        shape = RoundedCornerShape(24.dp),
                        color = Color(0xFF1D3B31),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = 18.dp)
                    ) {
                        Text(
                            text = header.tag!!,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(12.dp)
            ) {
                OutlinedButton(
                    onClick = onEdit,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) { Text("Editar encabezado") }
            }
        }
    }
}

@Composable
private fun PopularGrid(
    products: List<ProductUi>,
    onOpen: (ProductUi) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 120.dp, max = 260.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { p ->
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFECEFF1))
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = p.imageUrl.ifEmpty { null },
                    contentDescription = p.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFCFD8DC)),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(6.dp))
                Text(p.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(p.price, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
private fun CategorySection(
    category: CategoryUi,
    onOpenProduct: (ProductUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(category.name, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            category.products.forEach { p ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0xFFE8F0F2))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = p.imageUrl.ifEmpty { null },
                        contentDescription = p.title,
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFC8DCE1)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(p.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(p.price, color = MaterialTheme.colorScheme.primary)
                    }
                    Surface(
                        shape = RoundedCornerShape(999.dp),
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                    ) { Box(Modifier.size(28.dp)) }
                }
            }
        }
    }
}

@Composable
private fun EmptyBlock(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF3F4F6))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) { Text(text, color = Color(0xFF6B7280)) }
}

@Composable
private fun BottomBar2(selected: ServiceTab, onTabChange: (ServiceTab) -> Unit) {
    NavigationBar(containerColor = Color(0xFF2E584A)) {
        NavigationBarItem(
            selected = selected == ServiceTab.ORDERS,
            onClick = { onTabChange(ServiceTab.ORDERS) },
            icon = { Icon(Icons.Default.Menu, null) },
            label = { Text("Pedidos") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White.copy(alpha = 0.6f),
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = selected == ServiceTab.STORE,
            onClick = { onTabChange(ServiceTab.STORE) },
            icon = { Icon(Icons.Default.ShoppingCart, null) },
            label = { Text("Tienda") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White.copy(alpha = 0.6f),
                indicatorColor = Color.Transparent
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun PreviewServiceHome_Empty() {
    ServiceHomeScreen(
        header = ServiceHeaderUi(name = null, tag = "Tag"),
        popular = emptyList(),
        categories = emptyList(),
        selectedTab = ServiceTab.ORDERS,
        onTabChange = {},
        onAddCategory = {},
        onAddPopularProduct = {},
        onOpenProduct = {},
        onEditHeader = {},
        onLogout = {}
    )
}