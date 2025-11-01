package com.martinezzf.breakbitee.ui.screens.components


import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.height(52.dp),
        placeholder = { Text(placeholder, color = Color(0xFF9E9E9E), fontSize = 14.sp) },
        singleLine = singleLine,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF2F2F2),
            unfocusedContainerColor = Color(0xFFF2F2F2),
            disabledContainerColor = Color(0xFFF2F2F2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}
