package com.martinezzf.breakbitee.ui.userScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.martinezzf.breakbitee.ui.navegation.UserTab
import com.martinezzf.breakbitee.LocalDarkModeHandler


private val BannerGreen = Color(0xFF2E584A)

@Composable
fun UserProfileScreen(
    userName: String,
    userEmail: String,
    selectedTab: UserTab,
    onTabChange: (UserTab) -> Unit,
    onLogout: () -> Unit,
    onNotifications: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var darkMode by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomBarUser(selected = selectedTab, onTabChange = onTabChange)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BannerGreen)
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(36.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(userName, color = Color.White, fontWeight = FontWeight.Bold)
                        TextButton(onClick = onLogout) {
                            Text("cerrar sesión", color = Color.White, textDecoration = TextDecoration.Underline)
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Modo", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.weight(1f))

                val toggleDarkMode = LocalDarkModeHandler.current

                Switch(
                    checked = darkMode,
                    onCheckedChange = {
                        darkMode = it
                        toggleDarkMode(it)
                    }
                )


            }

            Spacer(Modifier.height(16.dp))

            Text("Correo Electrónico", fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp))
            Text(userEmail, modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp), color = Color.Gray)

            Spacer(Modifier.height(16.dp))

            Text("Nombre", fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Nombre") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    placeholder = { Text("Apellido (s)") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(20.dp))

            Column(Modifier.padding(horizontal = 16.dp)) {
                Text("Notificaciones", fontWeight = FontWeight.Bold)
                TextButton(onClick = onNotifications) {
                    Text("ver notificaciones", color = BannerGreen, textDecoration = TextDecoration.Underline)
                }
            }
        }
    }
}
