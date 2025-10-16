package com.grupo6.breakbiteuvg.ui.UserScreens

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grupo6.breakbiteuvg.R

private val BannerGreen = Color(0xFF2E584A)

enum class UserTab {HOME, HISTORY, PROFILE}

data class ServiceUi(
    val id: String,
    val name: String
)

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
            selected = selected == UserTab.HOME,
            onClick = { onTabChange(UserTab.HOME) },
            icon = { androidx.compose.material3.Icon(Icons.Default.ShoppingCart, contentDescription = "Inicio") },
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
            selected = selected == UserTab.HISTORY,
            onClick = { onTabChange(UserTab.HISTORY) },
            icon = { androidx.compose.material3.Icon(Icons.Default.Menu, contentDescription = "Historial") },
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
            selected = selected == UserTab.PROFILE,
            onClick = { onTabChange(UserTab.PROFILE) },
            icon = { androidx.compose.material3.Icon(Icons.Default.Person, contentDescription = "Perfil") },
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

                // Buscador
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
            BottomBarUser(
                selected = selectedTab,
                onTabChange = onTabChange
            )
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

/* Card de servicio (sin foto ni banner) */
@Composable
private fun ServiceCard(
    service: ServiceUi,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 0.dp,
        color = Color.White,
        shadowElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 96.dp)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Placeholder de imagen
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFE0E0E0))
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(service.name, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewUserHomeScreen() {
    val mock = listOf(
        ServiceUi("1", "Servicio 1"),
        ServiceUi("2", "Servicio 2"),
        ServiceUi("3", "Servicio 3")
    )
    UserHomeScreen(
        services = mock,
        onOpenService = {},
        selectedTab = UserTab.HOME,
        onTabChange = {}
    )
}