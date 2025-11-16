/**
 * Pantalla donde el usuario ve las notificaciones para confirmar sus pedidos.
 */

package com.martinezzf.breakbitee.ui.userScreens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val BannerGreen = Color(0xFF2E584A)
private val CardGreen   = Color(0xFF497766)

//Tipos de notificaciones
enum class NotificationType { RECEIVED, READY }

//Data class de notificacion
data class UserNotification(
    val id: String, //id de notificacion
    val type: NotificationType, //Tipo de notificacion
    val message: String //Mensaje de la notificacion
)

//Genera un mensaje por defecto
fun defaultMessageFor(type: NotificationType): String =
    when (type) {
        NotificationType.RECEIVED -> "El local ha recibido tu pedido correctamente"
        NotificationType.READY    -> "El pedido está listo para que lo vayas a recoger"
    }

//Composable de la pantalla de notificaciones
@Composable
fun NotificationsScreen(
    notifications: List<UserNotification>,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(BannerGreen)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                    Text(
                        text = "Notificaciones",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            notifications.forEach { notif ->
                NotificationCard(message = notif.message)
            }
        }
    }
}

//Composable de la carta de notificacion
@Composable
private fun NotificationCard(message: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = CardGreen,
        tonalElevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(BannerGreen),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(Modifier.width(12.dp))
            Text(
                text = message,
                color = Color.White
            )
        }
    }
}

