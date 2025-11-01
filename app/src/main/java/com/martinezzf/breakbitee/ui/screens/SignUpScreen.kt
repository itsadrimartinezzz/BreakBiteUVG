package com.martinezzf.breakbitee.ui.screens


import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.martinezzf.breakbitee.ui.screens.components.AppLogo
import com.martinezzf.breakbitee.ui.screens.components.AuthTextField
import com.martinezzf.breakbitee.ui.screens.components.GoogleButton
import com.martinezzf.breakbitee.ui.screens.components.LinkText
import com.martinezzf.breakbitee.ui.screens.components.PrimaryButton

@Composable
fun SignUpScreen(
    uvgEmail: String,
    onUvgEmailChange: (String) -> Unit,
    serviceEmail: String,
    onServiceEmailChange: (String) -> Unit,
    onContinueUvg: () -> Unit,
    onContinueService: () -> Unit,
    onGoToLogin: () -> Unit,
    onGoogle: () -> Unit,
    uvgError: String? = null,
    serviceError: String? = null,
    isProcessing: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(60.dp))

        AppLogo(modifier = Modifier.size(180.dp))

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Crea una cuenta",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(4.dp))

        Text(
            buildAnnotatedString {
                append("Ingresa tu correo de la\nuniversidad para registrarte en\n")
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF1B5E20),
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("BREAK BITE UVG")
                }
            },
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(24.dp))

        // Email UVG
        AuthTextField(
            value = uvgEmail,
            onValueChange = onUvgEmailChange,
            placeholder = "tucorreo@uvg.edu.gt",
            modifier = Modifier.fillMaxWidth()
        )
        if (!uvgError.isNullOrBlank()) {
            Spacer(Modifier.height(6.dp))
            Text(
                text = uvgError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(10.dp))

        PrimaryButton(
            text = if (isProcessing) "Procesando..." else "Continuar",
            onClick = onContinueUvg,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(16.dp))

        // Email Servicio
        AuthTextField(
            value = serviceEmail,
            onValueChange = onServiceEmailChange,
            placeholder = "correoempresa@empresa.ejemplo",
            modifier = Modifier.fillMaxWidth()
        )
        if (!serviceError.isNullOrBlank()) {
            Spacer(Modifier.height(6.dp))
            Text(
                text = serviceError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(10.dp))

        PrimaryButton(
            text = if (isProcessing) "Procesando..." else "Continuar como Servicio",
            onClick = onContinueService,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(12.dp))

        LinkText(
            prefix = "Si ya tiene una cuenta inicie sesión ",
            linkLabel = "aquí",
            onClick = onGoToLogin
        )

        Spacer(Modifier.height(12.dp))

        GoogleButton(
            onClick = onGoogle,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

