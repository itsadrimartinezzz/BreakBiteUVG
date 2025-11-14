package com.martinezzf.breakbitee.ui.userScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.martinezzf.breakbitee.R
import com.martinezzf.breakbitee.data.ServiceUi
import com.martinezzf.breakbitee.ui.navegation.UserTab

private val BannerGreen = Color(0xFF2E584A)

@Composable
fun BottomBarUser(
    selected: UserTab,
    onTabChange: (UserTab) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = BannerGreen
    ) {
        NavigationBarItem(
            selected = selected == UserTab.Home,
            onClick = { onTabChange(UserTab.Home) },
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Inicio") },
            label = { Text("Inicio", color = Color.White) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White.copy(alpha = 0.7f),
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = selected == UserTab.History,
            onClick = { onTabChange(UserTab.History) },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Historial") },
            label = { Text("Historial", color = Color.White) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White.copy(alpha = 0.7f),
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = selected == UserTab.Profile,
            onClick = { onTabChange(UserTab.Profile) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil", color = Color.White) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                selectedTextColor = Color.White,
                unselectedTextColor = Color.White.copy(alpha = 0.7f),
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun UserHomeScreen(
    services: List<ServiceUi>,
    onOpenService: (ServiceUi) -> Unit,
    selectedTab: UserTab,
    onTabChange: (UserTab) -> Unit
) {
    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BannerGreen)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_bu_blanco),
                    contentDescription = "BU",
                    modifier = Modifier
                        .height(28.dp)
                        .alpha(0.35f)
                )
                Spacer(Modifier.height(8.dp))

                TextField(
                    value = query,
                    onValueChange = { query = it },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    placeholder = { Text("Buscar") },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White.copy(alpha = 0.15f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.15f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedPlaceholderColor = Color.White.copy(alpha = 0.7f),
                        unfocusedPlaceholderColor = Color.White.copy(alpha = 0.7f),
                        focusedLeadingIconColor = Color.White,
                        unfocusedLeadingIconColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        bottomBar = {
            BottomBarUser(selected = selectedTab, onTabChange = onTabChange)
        }
    ) { padding ->
        val filtered = remember(query, services) {
            if (query.isBlank()) services
            else services.filter { it.name.contains(query, ignoreCase = true) }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(filtered, key = { it.id }) { service ->
                ServiceCard(
                    service = service,
                    onClick = { onOpenService(service) }
                )
            }
        }
    }
}

@Composable
private fun ServiceCard(
    service: ServiceUi,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        // 🖼 Fondo con AsyncImage (para agregar imagen de fondo)
        AsyncImage(
            model = service.bannerUrl, // puedes cambiar la URL según necesites
            contentDescription = "${service.name} background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // Sombra oscura para mejorar contraste
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.35f))
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo cuadrado del restaurante (mantiene su posición)
            AsyncImage(
                model = service.imageUrl,
                contentDescription = "${service.name} logo",
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = service.name,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = service.time,
                    color = Color.White.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
