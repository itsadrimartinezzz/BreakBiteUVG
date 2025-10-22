package com.grupo6.breakbiteuvg.ui.navegation

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding

// 👇 importa TUS destinos ya definidos en otro archivo
import com.grupo6.breakbiteuvg.ui.navegation.LoginDestination
import com.grupo6.breakbiteuvg.ui.navegation.SignUpDestination
import com.grupo6.breakbiteuvg.ui.navegation.HomeDestination
import com.grupo6.breakbiteuvg.ui.navegation.ProfileDestination
import com.grupo6.breakbiteuvg.ui.navegation.MainDestination

// 👇 importa TU enum UserTab ya definido aparte
import com.grupo6.breakbiteuvg.ui.navegation.UserTab

// Screens reales
import com.grupo6.breakbiteuvg.ui.screens.LoginScreen
import com.grupo6.breakbiteuvg.ui.screens.SignUpScreen
import com.grupo6.breakbiteuvg.ui.UserScreens.UserHomeScreen
import com.grupo6.breakbiteuvg.ui.UserScreens.UserOrderHistoryScreen
import com.grupo6.breakbiteuvg.ui.UserScreens.UserProfileScreen

@Composable
fun AppNav() {
    val nav = rememberNavController()

    // Estado simple del usuario (para que Perfil muestre algo)
    var userName by rememberSaveable { mutableStateOf("Usuario") }
    var userEmail by rememberSaveable { mutableStateOf("usuario@uvg.edu.gt") }

    NavHost(navController = nav, startDestination = LoginDestination) {

        // LOGIN (start)
        composable<LoginDestination> {
            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }

            LoginScreen(
                email = email,
                onEmailChange = { email = it },
                password = password,
                onPasswordChange = { password = it },
                onContinue = {
                    if (email.isNotBlank()) {
                        userEmail = email
                        userName = email.substringBefore("@")
                            .replaceFirstChar { c -> c.titlecase() }
                    }
                    nav.navigate(MainDestination) {
                        popUpTo(LoginDestination) { inclusive = true }
                    }
                },
                onGoToSignUp = { nav.navigate(SignUpDestination) }
            )
        }

        // SIGN UP
        composable<SignUpDestination> {
            var uvg by rememberSaveable { mutableStateOf("") }
            var service by rememberSaveable { mutableStateOf("") }

            SignUpScreen(
                uvgEmail = uvg,
                onUvgEmailChange = { uvg = it },
                serviceEmail = service,
                onServiceEmailChange = { service = it },
                onContinueUvg = {
                    if (uvg.isNotBlank()) {
                        userEmail = uvg
                        userName = uvg.substringBefore("@")
                            .replaceFirstChar { c -> c.titlecase() }
                    }
                    nav.navigate(MainDestination) {
                        popUpTo(LoginDestination) { inclusive = true }
                    }
                },
                onContinueService = {
                    if (service.isNotBlank()) {
                        userEmail = service
                        userName = service.substringBefore("@")
                            .replaceFirstChar { c -> c.titlecase() }
                    }
                    nav.navigate(MainDestination) {
                        popUpTo(LoginDestination) { inclusive = true }
                    }
                },
                onGoToLogin = { nav.popBackStack() },
                onGoogle = {
                    // setea datos si usas Google
                    nav.navigate(MainDestination) {
                        popUpTo(LoginDestination) { inclusive = true }
                    }
                }
            )
        }

        // MAIN (contenedor con 3 tabs)
        composable<MainDestination> {
            var selectedTab by rememberSaveable { mutableStateOf(UserTab.Home) }

            when (selectedTab) {
                UserTab.Home -> {
                    UserHomeScreen(
                        services = emptyList(),                 // sin data por ahora
                        onOpenService = { /* no-op */ },
                        selectedTab = selectedTab,
                        onTabChange = { selectedTab = it }      // cambia entre Home/Orders/Profile
                    )
                }
                UserTab.Orders -> {
                    UserOrderHistoryScreen(
                        orders = emptyList(),                 // TODO: conecta tu lista real
                        onOpenOrderDetail = { orderId ->
                            // Si ya tienes destino de detalle, descomenta:
                            // nav.navigate(OrderDetailDestination(orderId))
                        },
                        selectedTab = selectedTab,
                        onTabChange = { selectedTab = it }    // <- volver a Home/Profile desde Orders
                    )
                }
                UserTab.Profile -> {
                    UserProfileScreen(
                        userName = userName,
                        userEmail = userEmail,
                        selectedTab = selectedTab,
                        onTabChange = { selectedTab = it },
                        onLogout = {
                            userName = "Usuario"
                            userEmail = "usuario@uvg.edu.gt"
                            nav.navigate(LoginDestination) {
                                popUpTo(MainDestination) { inclusive = true }
                            }
                        },
                        onNotifications = { /* no-op */ }
                    )
                }
                // si tu enum tiene más tabs (p. ej. Notifications),
                // agrégalas aquí o deja un else:
                else -> {
                    Text("Sección no implementada", modifier = Modifier.padding(24.dp))
                }
            }
        }
    }
}
