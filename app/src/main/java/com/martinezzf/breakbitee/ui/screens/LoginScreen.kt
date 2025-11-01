package com.martinezzf.breakbitee.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.martinezzf.breakbitee.ui.screens.components.LinkText
import com.martinezzf.breakbitee.ui.screens.components.PrimaryButton

@Composable
fun LoginScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onContinue: () -> Unit,
    onGoToSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // LOGO
        AppLogo(
            modifier = Modifier
                .size(180.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "INICIAR SESIÓN",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        // SUBTITLE
        Text(
            buildAnnotatedString {
                append("Inicia Sesión en ")
                withStyle(style = SpanStyle(color = Color(0xFF1B5E20), fontWeight = FontWeight.SemiBold)) {
                    append("BREAK BITE UVG")
                }
                append("\ncon el correo de la universidad")
            },
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // TEXT FIELDS
        AuthTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholder = "tucorreo@uvg.edu.gt",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        AuthTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "password",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // BUTTON
        PrimaryButton(
            text = "Continuar",
            onClick = onContinue,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // LINK
        LinkText(
            prefix = "Si no tiene una cuenta, cree una ",
            linkLabel = "aquí",
            onClick = onGoToSignUp
        )
    }
}
