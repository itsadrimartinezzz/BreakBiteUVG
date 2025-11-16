/**
 * MainActivity
 */

package com.martinezzf.breakbitee


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.martinezzf.breakbitee.ui.navegation.AppNav
import com.martinezzf.breakbitee.ui.theme.BreakBiteUVGTheme

val LocalDarkModeHandler = compositionLocalOf<(Boolean) -> Unit> { {} }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //Variable para la funcion de modo oscuro.
            var darkMode by remember { mutableStateOf(false) }

            CompositionLocalProvider(
                LocalDarkModeHandler provides { darkMode = it }
            ) {
                BreakBiteUVGTheme(darkTheme = darkMode) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppNav(
                            onToggleDarkMode = { darkMode = it }
                        )
                    }
                }
            }
        }
    }
}