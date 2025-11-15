package com.martinezzf.breakbitee.ui.navegation

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.martinezzf.breakbitee.data.*
import com.martinezzf.breakbitee.ui.userScreens.*
import com.martinezzf.breakbitee.ui.screens.LoginScreen
import com.martinezzf.breakbitee.ui.screens.SignUpScreen
import com.martinezzf.breakbitee.ui.serviceScreens.*
import kotlinx.serialization.json.Json
import com.martinezzf.breakbitee.data.ServiceOrderUi
import com.martinezzf.breakbitee.data.UserOrderItemUi
import androidx.compose.runtime.compositionLocalOf
import com.martinezzf.breakbitee.ui.navegation.LocalAllItems
import com.martinezzf.breakbitee.ui.userScreens.NotificationsScreen
import com.martinezzf.breakbitee.ui.userScreens.UserOrderDetailScreen
import com.martinezzf.breakbitee.ui.userScreens.UserOrderDetailUi






data class ServiceInfo(
    val id: String,
    val name: String,
    val displayName: String,
    val tag: String,
    val logoUrl: String,
    val password: String,
    val backgroundUrl: String = ""
)

@SuppressLint("SimpleDateFormat")
@Composable
fun AppNav(onToggleDarkMode: (Boolean) -> Unit) {
    var isDarkMode by rememberSaveable { mutableStateOf(false) }

    val nav = rememberNavController()
    var userName by rememberSaveable { mutableStateOf("Usuario") }
    var userEmail by rememberSaveable { mutableStateOf("usuario@uvg.edu.gt") }

    var orders by rememberSaveable { mutableStateOf(listOf<OrderUi>()) }
    var allItems by remember { mutableStateOf(mutableListOf<UserOrderItemUi>()) }
    var notifications by remember { mutableStateOf(listOf<UserNotification>()) }

    // === Restaurantes ===
    val servicesInfo = remember {
        listOf(
            ServiceInfo(
                id = "1",
                name = "Cafe Barista",
                displayName = "Barista",
                tag = "Edif. F – UVG",
                logoUrl = "https://media.licdn.com/dms/image/v2/C4D0BAQG0iaY0mTFOtg/company-logo_200_200/company-logo_200_200/0/1676502742315/caf_barista_logo?e=2147483647&v=beta&t=RdzwCEyGJJeYckb8KiViPVjlcNdx3t6eEXkxJXe_9g0",
                password = "barista123",
                backgroundUrl = "https://www.prensalibre.com/wp-content/uploads/2018/09/Cafe-barista-portada.png?quality=52"
            ),
            ServiceInfo(
                id = "2",
                name = "& Cafe",
                displayName = "&Cafe",
                tag = "Edif. T – UVG",
                logoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTHCi3JodBohfzg0Cr5tQ_Z9nlZuLo58XNDg&s",
                password = "elcafe123",
                backgroundUrl = "https://andcafe.com/media/k2/items/cache/2fa67f482133f1c934235b73c2a03954_M.jpg?t=20220418_183154"
            ),
            ServiceInfo(
                id = "3",
                name = "Gitane",
                displayName = "Gitane",
                tag = "Edif. S – UVG",
                logoUrl = "https://pedidosya.dhmedia.io/image/pedidosya/restaurants/cafe-gitane.png",
                password = "gitane123",
                backgroundUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjNeJaVfchBpvb1M__tBKHT_QkZOQNgHdpDw&s"
            ),
            ServiceInfo(
                id = "4",
                name = "Go Green",
                displayName = "Gogreen",
                tag = "Edif. D – UVG",
                logoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4lqfr6uvS-Bm6YAaHyaUfXcqjEw2HSYB15g&s",
                password = "gogreen123",
                backgroundUrl = "https://static.vecteezy.com/system/resources/previews/006/224/670/non_2x/go-green-concept-banner-with-lush-green-foliage-illustration-vector.jpg"
            ),
            ServiceInfo(
                id = "5",
                name = "Panitos y algo mas",
                displayName = "Panitos",
                tag = "Edif. O – UVG",
                logoUrl = "https://pedidosya.dhmedia.io/image/pedidosya/restaurants/panitos-y-algo-mas-logo.jpg",
                password = "panitos123",
                backgroundUrl = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/14/32/b9/7b/planta-baja.jpg?w=900&h=500&s=1"
            ),
            ServiceInfo(
                id = "6",
                name = "Mixtas Frankfurt",
                displayName = "Frankfurt",
                tag = "Cafetería Central",
                logoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSx57XNb3GJakIiMYtpxB19tr2V7BGVsdV8tQ&s",
                password = "frankfurt123",
                backgroundUrl = "https://degusta-pictures-hd.b-cdn.net/16_100341_r_0.jpg?v=2382"
            ),
            ServiceInfo(
                id = "7",
                name = "Golden Harvest",
                displayName = "Golden",
                tag = "Edif. P – UVG",
                logoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHnFvfrTUk3L5N5eWP08HyG8BTGiAEqxaH7Q&s",
                password = "golden123",
                backgroundUrl = "https://goldenharvesting.com/wp-content/uploads/2025/07/main-logo.png"
            )
        )
    }

    val services = servicesInfo.map {
        ServiceUi(
            id = it.id,
            name = it.name,
            imageUrl = it.logoUrl,
            bannerUrl = it.backgroundUrl,
            time = "15-20 min"
        )
    }

    var serviceSelectedTab by rememberSaveable { mutableStateOf(ServiceOrderTab.ORDERS) }
    var currentServiceInfo by remember { mutableStateOf<ServiceInfo?>(null) }

    val serviceHeader = remember(currentServiceInfo) {
        currentServiceInfo?.let {
            ServiceHeaderUi(
                name = it.displayName,
                tag = it.tag,
                logoUrl = it.logoUrl
            )
        } ?: ServiceHeaderUi(name = "Servicio", tag = "UVG", logoUrl = "")
    }

    var serviceOrders by rememberSaveable {
        mutableStateOf(
            listOf(
                ServiceOrderUi("BB-2001", "Pendiente", "Usuario", 3, "Q95"),
                ServiceOrderUi("BB-2002", "En preparación", "Adriana", 2, "Q62")
            )
        )
    }

    fun isUvgEmail(s: String) = s.endsWith("@uvg.edu.gt", ignoreCase = true)

    // 🔹 Fix: Composable context correcto
    CompositionLocalProvider(LocalAllItems provides allItems) {
        NavHost(navController = nav, startDestination = LoginDestination) {
            // === LOGIN ===
            composable<LoginDestination> {
                var email by rememberSaveable { mutableStateOf("") }
                var password by rememberSaveable { mutableStateOf("") }
                var error by rememberSaveable { mutableStateOf<String?>(null) }

                LoginScreen(
                    email = email,
                    onEmailChange = { email = it; error = null },
                    password = password,
                    onPasswordChange = { password = it; error = null },
                    onContinue = {
                        if (isUvgEmail(email)) {
                            userEmail = email
                            userName = email.substringBefore("@").replaceFirstChar { it.titlecase() }
                            nav.navigate(MainDestination) {
                                popUpTo(LoginDestination) { inclusive = true }
                            }
                        } else {
                            val match = servicesInfo.find {
                                it.name.equals(email, true) || it.displayName.equals(email, true)
                            }
                            if (match != null && match.password == password) {
                                currentServiceInfo = match
                                nav.navigate(ServiceOrdersDestination) {
                                    popUpTo(LoginDestination) { inclusive = true }
                                }
                            } else {
                                error = "Credenciales inválidas"
                            }
                        }
                    },
                    onGoToSignUp = { nav.navigate(SignUpDestination) }
                )
            }

            // === SIGN UP ===
            composable<SignUpDestination> {
                SignUpScreen(
                    uvgEmail = "",
                    onUvgEmailChange = {},
                    serviceEmail = "",
                    onServiceEmailChange = {},
                    onContinueUvg = { nav.navigate(MainDestination) },
                    onContinueService = { nav.navigate(ServiceOrdersDestination) },
                    onGoToLogin = { nav.popBackStack() },
                    onGoogle = { nav.navigate(MainDestination) }
                )
            }

            // === HOME (Usuarios) ===
            composable<MainDestination> {
                var selectedTab by rememberSaveable { mutableStateOf(UserTab.Home) }

                when (selectedTab) {
                    UserTab.Home -> UserHomeScreen(
                        services = services,
                        onOpenService = { nav.navigate(ServiceDestination(it.id)) },
                        selectedTab = selectedTab,
                        onTabChange = { selectedTab = it }
                    )
                    UserTab.Orders, UserTab.History -> UserOrderHistoryScreen(
                        orders = orders,
                        allItems = allItems,
                        onOpenOrderDetail = { nav.navigate(OrderDetailDestination(it.id, it.serviceName)) },


                        selectedTab = selectedTab,
                        onTabChange = { selectedTab = it }
                    )
                    UserTab.Profile -> UserProfileScreen(
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
                        onNotifications = { nav.navigate(NotificationsDestination) }
                    )
                    else -> {}
                }
            }

            // === SERVICIO SELECCIONADO ===
            composable<ServiceDestination> { backStackEntry ->
                val args = backStackEntry.toRoute<ServiceDestination>()
                val service = services.find { it.id == args.serviceId }

                if (service != null) {
                    val menu = remember(service.id) { FakeApi.getMenuForService(service.id) }
                    var showConfirmation by remember { mutableStateOf(false) }

                    if (showConfirmation) {
                        OrderCompletedScreenAuto(
                            onDone = {
                                showConfirmation = false
                                nav.navigate(MainDestination) {
                                    popUpTo(MainDestination) { inclusive = true }
                                }
                            },
                            delayMs = 3000L
                        )
                    } else {
                        UserServiceScreen(
                            serviceName = service.name,
                            popular = menu.first,
                            categories = menu.second,
                            onBack = { nav.popBackStack() },
                            onOpenProduct = { product ->
                                val detail = ProductDetailUi(
                                    id = product.id,
                                    name = product.name,
                                    description = product.descripcion.ifBlank {
                                        "Producto disponible en ${service.name}"
                                    },
                                    basePriceQ = product.priceLabel.replace("Q", "").trim().toIntOrNull() ?: 25,
                                    imageUrl = product.imageUrl,
                                    parameters = listOf(
                                        ProductParameterUi(
                                            id = "size",
                                            name = "Tamaño",
                                            options = listOf(
                                                ProductOptionUi("opt12", "Normal"),
                                                ProductOptionUi("opt16", "Grande", 4)
                                            )
                                        )
                                    ),
                                    serviceName = service.name
                                )

                                val encoded = Json.encodeToString(ProductDetailUi.serializer(), detail)
                                nav.navigate(ProductDestination(encoded, service.id))
                            },
                            onCompleteOrder = {
                                showConfirmation = true
                                notifications = notifications + UserNotification(
                                    "n${System.currentTimeMillis()}",
                                    NotificationType.RECEIVED,
                                    defaultMessageFor(NotificationType.RECEIVED)
                                )
                            }
                        )
                    }
                } else Text("Servicio no encontrado")
            }

            // === PRODUCT DETAIL ===
            composable<ProductDestination> { entry ->
                val args = entry.toRoute<ProductDestination>()
                val product = remember {
                    Json.decodeFromString(ProductDetailUi.serializer(), args.productJson)
                }

                UserProductScreen(
                    product = product,
                    onBack = { nav.popBackStack() },
                    onAddToOrder = { added ->
                        val existingOrder = orders.find { it.serviceName == added.serviceName }
                        var currentOrderId: String

                        if (existingOrder != null) {
                            orders = orders.map {
                                if (it.serviceName == added.serviceName) {
                                    it.copy(
                                        totalQ = it.totalQ + added.basePriceQ,
                                        productCount = it.productCount + 1
                                    )
                                } else it
                            }
                            currentOrderId = existingOrder.id
                        } else {
                            val newOrder = OrderUi(
                                id = "order-${System.currentTimeMillis()}",
                                serviceName = added.serviceName,
                                totalQ = added.basePriceQ,
                                productCount = 1,
                                dateTime = "hoy - ${java.text.SimpleDateFormat("hh:mm a").format(java.util.Date())}",
                                status = "Pendiente"
                            )
                            orders = orders + newOrder

                            serviceOrders = serviceOrders + ServiceOrderUi(
                                id = newOrder.id,
                                estado = "Pendiente",
                                cliente = userName,
                                cantidadProductos = 1,
                                total = "Q${added.basePriceQ}"
                            )

                            currentOrderId = newOrder.id
                        }

                        allItems.add(
                            UserOrderItemUi(
                                id = "${added.serviceName}-${System.currentTimeMillis()}",
                                name = added.name,
                                priceQ = added.basePriceQ,
                                imageUrl = added.imageUrl,
                                quantity = 1,
                                serviceId = added.serviceName,
                                orderId = currentOrderId // ✅ aquí ahora sí existe la variable
                            )
                        )

                        nav.popBackStack()
                    }

                )
            }

            // === VISTA ESTABLECIMIENTO ===
            composable<ServiceOrdersDestination> {
                ServiceOrdersScreen(
                    negocio = serviceHeader.name ?: "Tu negocio",
                    tag = serviceHeader.tag ?: "Sin ubicación",
                    logoUrl = serviceHeader.logoUrl,
                    orders = serviceOrders,
                    selectedTab = serviceSelectedTab,
                    onTabChange = {
                        serviceSelectedTab = it
                        if (it == ServiceOrderTab.STORE) nav.navigate(ServiceStoreDestination)
                    },
                    onEditHeader = {},
                    onOpenOrder = { order ->
                        nav.navigate(ServiceOrderDetailDestination(order.id))
                    },
                    onLogout = {
                        currentServiceInfo = null
                        nav.navigate(LoginDestination) {
                            popUpTo(ServiceOrdersDestination) { inclusive = true }
                        }
                    }
                )
            }


            // === DETALLE PEDIDO ===
            composable<ServiceOrderDetailDestination> { entry ->
                val args = entry.toRoute<ServiceOrderDetailDestination>()
                val order = serviceOrders.find { it.id == args.orderId }

                if (order != null) {
                    val orderItems = allItems
                        .filter { it.orderId == order.id } // ✅ Ahora filtra correctamente por el ID del pedido
                        .map {
                            OrderItemUi(
                                id = it.id,
                                nombre = it.name,
                                precio = it.priceQ,
                                imageUrl = it.imageUrl ?: "",
                                listo = false
                            )
                        }

                    ServiceOrderDetailScreen(
                        order = order,
                        items = orderItems,
                        onBack = { nav.popBackStack() },
                        onComplete = {
                            serviceOrders = serviceOrders.map {
                                if (it.id == order.id) it.copy(estado = "Completado") else it
                            }
                            nav.popBackStack()
                        }
                    )
                } else {
                    Text("Pedido no encontrado")
                }
            }

            // === DETALLE DE PEDIDO DE USUARIO ===
            composable<OrderDetailDestination> { backStackEntry ->
                val args = backStackEntry.toRoute<OrderDetailDestination>()

                // 🔹 Filtra solo los ítems que pertenecen al pedido Y al restaurante correcto
                val selectedOrderItems = allItems.filter { item ->
                    item.orderId == args.orderId && item.serviceId == args.serviceName
                }

                println("DEBUG: buscando ${args.orderId} de ${args.serviceName}, encontrados ${selectedOrderItems.size}")

                if (selectedOrderItems.isNotEmpty()) {
                    val orderUi = UserOrderDetailUi(
                        id = args.orderId,
                        serviceName = args.serviceName,
                        items = selectedOrderItems.map {
                            com.martinezzf.breakbitee.ui.userScreens.UserOrderItemUi(
                                id = it.id,
                                name = it.name,
                                priceQ = it.priceQ,
                                imageUrl = it.imageUrl,
                                quantity = it.quantity
                            )
                        },
                        status = "Completado"
                    )

                    UserOrderDetailScreen(
                        data = orderUi,
                        onBack = { nav.popBackStack() }
                    )
                } else {
                    Text("Pedido no encontrado")
                }
            }


            // === NOTIFICACIONES ===
            composable<NotificationsDestination> {
                NotificationsScreen(
                    notifications = notifications,
                    onBack = { nav.popBackStack() }
                )
            }






            // === NUEVO PRODUCTO ===
            composable<NewProductDestination> {
                NewProductScreen(onBack = { nav.popBackStack() })
            }
        }
    }
}
