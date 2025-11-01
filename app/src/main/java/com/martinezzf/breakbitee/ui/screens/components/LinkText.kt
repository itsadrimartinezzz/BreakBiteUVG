package com.martinezzf.breakbitee.ui.screens.components


import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

@Composable
fun LinkText(
    prefix: String,
    linkLabel: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val linkColor = Color(0xFF1B5E20)

    val text = buildAnnotatedString {
        append(prefix)
        withStyle(
            SpanStyle(
                color = linkColor,
                fontWeight = FontWeight.SemiBold,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(linkLabel)
        }
    }
    Text(text = text, modifier = modifier.clickable { onClick() })
}
