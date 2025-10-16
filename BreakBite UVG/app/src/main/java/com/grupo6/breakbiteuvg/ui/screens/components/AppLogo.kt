package com.grupo6.breakbiteuvg.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import com.grupo6.breakbiteuvg.R

@Composable
fun AppLogo(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.logo_breakbite),
        contentDescription = "BreakBite Logo",
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}
