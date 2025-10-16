package com.grupo6.breakbiteuvg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import com.grupo6.breakbiteuvg.ui.screens.LoginScreen
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

/*en el main activity solo tenemos puesto el login screen porque lo estaba probando al inicio
ignoren esto :D
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var email by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

                    LoginScreen(
                        email = email,
                        onEmailChange = { email = it },
                        password = password,
                        onPasswordChange = { password = it },
                        onContinue = { /* TODO: Acción continuar */ },
                        onGoToSignUp = { /* TODO: Navegar a SignUp */ }
                    )
                }
            }
        }
    }
}
