package com.grupo6.breakbiteuvg.ui.UserScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grupo6.breakbiteuvg.R

private val BannerGreen = Color(0xFF2E584A)

@Composable
fun OrderCompletedScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BannerGreen),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // Logo BU
            Image(
                painter = painterResource(id = R.drawable.ic_bu_blanco),
                contentDescription = "BU",
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(1.9f) // ajusta si tu asset es más alto/ancho
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "HEMOS ENVIADO\nTU PEDIDO",
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 32.sp,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(Modifier.height(24.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "Completado",
                modifier = Modifier
                    .size(64.dp)
            )
        }
    }
}

/*
//Chon, usá esta madre cuando hagas el backend para hacer lo de volver automáticamente a home :D
@Composable
fun OrderCompletedScreenAuto(onDone: () -> Unit, delayMs: Long = 2000L) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(delayMs)
        onDone()
    }
    OrderCompletedScreen()
}
*/

@Preview(showBackground = true)
@Composable
private fun PreviewOrderCompletedScreen() {
    MaterialTheme {
        OrderCompletedScreen()
    }
}
