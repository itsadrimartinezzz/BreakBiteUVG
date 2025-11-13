package com.martinezzf.breakbitee.ui.navegation

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.martinezzf.breakbitee.data.ServiceUi
import com.martinezzf.breakbitee.data.FakeApi
import com.martinezzf.breakbitee.data.OrderUi
import com.martinezzf.breakbitee.data.ProductDetailUi
import com.martinezzf.breakbitee.data.ProductOptionUi
import com.martinezzf.breakbitee.data.ProductParameterUi
import com.martinezzf.breakbitee.ui.userScreens.*
import com.martinezzf.breakbitee.ui.screens.LoginScreen
import com.martinezzf.breakbitee.ui.screens.SignUpScreen
import com.martinezzf.breakbitee.ui.serviceScreens.ServiceOrdersScreen
import com.martinezzf.breakbitee.ui.serviceScreens.ServiceHomeScreen
import com.martinezzf.breakbitee.ui.serviceScreens.ServiceOrderDetailScreen
import com.martinezzf.breakbitee.ui.serviceScreens.NewProductScreen
import com.martinezzf.breakbitee.ui.serviceScreens.ServiceOrderUi as SOrderUi
import com.martinezzf.breakbitee.ui.serviceScreens.OrderItemUi as SOrderItemUi
import com.martinezzf.breakbitee.ui.serviceScreens.ServiceHeaderUi as SHeaderUi
import com.martinezzf.breakbitee.ui.serviceScreens.ServiceOrderTab as SOrderTab
import com.martinezzf.breakbitee.ui.serviceScreens.ServiceTab as SServiceTab
import com.martinezzf.breakbitee.ui.serviceScreens.ProductUi as SProductUi
import com.martinezzf.breakbitee.ui.serviceScreens.CategoryUi as SCategoryUi

import kotlinx.serialization.json.Json
import androidx.compose.runtime.compositionLocalOf

val LocalAllItems = compositionLocalOf { listOf<UserOrderItemUi>() }


data class ServiceInfo(
    val id: String,
    val name: String,
    val displayName: String,
    val tag: String,
    val logoUrl: String,
    val password: String
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


    val servicesInfo = remember {
        listOf(
            ServiceInfo(
                id = "1",
                name = "Cafe Barista",
                displayName = "Barista",
                tag = "Edif. F – UVG",
                logoUrl = "https://media.licdn.com/dms/image/v2/C4D0BAQG0iaY0mTFOtg/company-logo_200_200/company-logo_200_200/0/1676502742315/caf_barista_logo?e=2147483647&v=beta&t=RdzwCEyGJJeYckb8KiViPVjlcNdx3t6eEXkxJXe_9g0",
                password = "barista123"
            ),
            ServiceInfo(
                id = "2",
                name = "& Cafe",
                displayName = "&Cafe",
                tag = "Edif. T – UVG",
                logoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTHCi3JodBohfzg0Cr5tQ_Z9nlZuLo58XNDg&s",
                password = "elcafe123"
            ),
            ServiceInfo(
                id = "3",
                name = "Gitane",
                displayName = "Gitane",
                tag = "Edif. S – UVG",
                logoUrl = "https://pedidosya.dhmedia.io/image/pedidosya/restaurants/cafe-gitane.png",
                password = "gitane123"
            ),
            ServiceInfo(
                id = "4",
                name = "Go Green",
                displayName = "Gogreen",
                tag = "Edif. D – UVG",
                logoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4lqfr6uvS-Bm6YAaHyaUfXcqjEw2HSYB15g&s",
                password = "gogreen123"
            ),
            ServiceInfo(
                id = "5",
                name = "Panitos y algo mas",
                displayName = "Panitos",
                tag = "Edif. O – UVG",
                logoUrl = "https://pedidosya.dhmedia.io/image/pedidosya/restaurants/panitos-y-algo-mas-logo.jpg",
                password = "panitos123"
            ),
            ServiceInfo(
                id = "6",
                name = "Mixtas Frankfurt",
                displayName = "Frankfurt",
                tag = "Cafetería Central",
                logoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSx57XNb3GJakIiMYtpxB19tr2V7BGVsdV8tQ&s",
                password = "frankfurt123"
            ),
            ServiceInfo(
                id = "7",
                name = "Golden Harvest",
                displayName = "Golden",
                tag = "Edif. P – UVG",
                logoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHnFvfrTUk3L5N5eWP08HyG8BTGiAEqxaH7Q&s",
                password = "golden123"
            )
        )
    }

    val services = servicesInfo.map { info ->
        ServiceUi(
            id = info.id,
            name = info.name,
            imageUrl = "",
            bannerUrl = info.logoUrl,
            time = "15-20 min"
        )
    }


    var serviceSelectedTab by rememberSaveable { mutableStateOf(SOrderTab.ORDERS) }


    var currentServiceInfo by remember { mutableStateOf<ServiceInfo?>(null) }


    val serviceHeader = remember(currentServiceInfo) {
        currentServiceInfo?.let { info ->
            SHeaderUi(
                name = info.displayName,
                tag = info.tag,
                logoUrl = info.logoUrl
            )
        } ?: SHeaderUi(name = "Servicio", tag = "UVG", logoUrl = "")
    }

    var serviceOrders by rememberSaveable {
        mutableStateOf(
            listOf(
                SOrderUi("BB-2001","Pendiente","Usuario",3,"Q95"),
                SOrderUi("BB-2002","En preparación","Adriana",2,"Q62")
            )
        )
    }

    val serviceOrderItems by remember {
        mutableStateOf(
            mapOf(
                "BB-2001" to listOf(
                    SOrderItemUi(
                        id = "i1",
                        nombre = "Cappuccino",
                        precio = 26,
                        listo = true,
                        imageUrl = "https://www.nespresso.com/coffee-blog/sites/default/files/2024-08/nespresso-recipes-CAPPUCCINO-BANANA-SESAME-SEEDS.jpg"
                    ),
                    SOrderItemUi(
                        id = "i2",
                        nombre = "Menu, Panini - Pollo Pesto",
                        precio = 51,
                        listo = true,
                        imageUrl = "https://www.lecafeguatemala.com/_uploads/menus/imgs/full/693-20230505143758.jpg"
                    ),
                    SOrderItemUi(
                        id = "i3",
                        nombre = "Galleta Chocochip",
                        precio = 18,
                        listo = true,
                        imageUrl = "https://www.fifteenspatulas.com/wp-content/uploads/2020/08/Chocolate-Muffins-16.jpg"
                    )
                ),
                "BB-2002" to listOf(
                    SOrderItemUi(
                        id = "i4",
                        nombre = "Latte 12oz",
                        precio = 28,
                        listo = true,
                        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEzen5N3wvWNph_2_zzQQGKqNhLvRZCaweBg&s"
                    ),
                    SOrderItemUi(
                        id = "i5",
                        nombre = "Americano",
                        precio = 21,
                        listo = true,
                        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYfE4fnUPGLsOY-6HVFZIXXR_8W4yT0bUKkQ&s"
                    )
                )
            )
        )
    }


    val serviceMenu = remember(currentServiceInfo) {
        currentServiceInfo?.let { FakeApi.getMenuForService(it.id) }
            ?: Pair(emptyList(), emptyList())
    }

    val servicePopular = remember(serviceMenu) {
        serviceMenu.first.map { product ->
            SProductUi(
                id = product.id,
                title = product.name,
                price = product.priceLabel,
                imageUrl = product.imageUrl
            )
        }
    }

    val serviceCategories = remember(serviceMenu) {
        serviceMenu.second.map { category ->
            SCategoryUi(
                id = category.id,
                name = category.name,
                products = category.products.map { product ->
                    SProductUi(
                        id = product.id,
                        title = product.name,
                        price = product.priceLabel,
                        imageUrl = product.imageUrl
                    )
                }
            )
        }
    }

    fun isUvgEmail(s: String) = s.endsWith("@uvg.edu.gt", ignoreCase = true)

    CompositionLocalProvider(LocalAllItems provides allItems) {
        NavHost(navController = nav, startDestination = LoginDestination) {

            // LOGIN
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
                            nav.navigate(MainDestination) { popUpTo(LoginDestination) { inclusive = true } }
                            return@LoginScreen
                        }


                        val inputName = email.trim()
                        val matchedService = servicesInfo.find {
                            it.name.equals(inputName, ignoreCase = true) ||
                                    it.displayName.equals(inputName, ignoreCase = true)
                        }

                        if (matchedService != null && matchedService.password == password) {

                            currentServiceInfo = matchedService
                            userEmail = "${matchedService.name}@service.local"
                            userName = matchedService.displayName
                            nav.navigate(ServiceOrdersDestination) {
                                popUpTo(LoginDestination) { inclusive = true }
                            }
                        } else {
                            error = "Credenciales inválidas"
                        }
                    },
                    onGoToSignUp = { nav.navigate(SignUpDestination) }
                )
            }

            // SIGNUP
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
                            userName = uvg.substringBefore("@").replaceFirstChar { it.titlecase() }
                        }
                        nav.navigate(MainDestination) { popUpTo(LoginDestination) { inclusive = true } }
                    },
                    onContinueService = {
                        if (service.isNotBlank()) {

                            val matchedService = servicesInfo.find {
                                it.name.equals(service, ignoreCase = true) ||
                                        it.displayName.equals(service, ignoreCase = true)
                            }

                            if (matchedService != null) {
                                currentServiceInfo = matchedService
                                userEmail = "${matchedService.name}@service.local"
                                userName = matchedService.displayName
                            } else {
                                userEmail = "$service@service.local"
                                userName = service.replaceFirstChar { it.titlecase() }
                            }
                        }
                        nav.navigate(ServiceOrdersDestination) { popUpTo(LoginDestination) { inclusive = true } }
                    },
                    onGoToLogin = { nav.popBackStack() },
                    onGoogle = { nav.navigate(MainDestination) { popUpTo(LoginDestination) { inclusive = true } } }
                )
            }

            // MAIN (estudiantes)
            composable<MainDestination> {
                var selectedTab by rememberSaveable { mutableStateOf(UserTab.Home) }

                when (selectedTab) {
                    UserTab.Home -> {
                        UserHomeScreen(
                            services = services,
                            onOpenService = { nav.navigate(ServiceDestination(it.id)) },
                            selectedTab = selectedTab,
                            onTabChange = { selectedTab = it }
                        )
                    }
                    UserTab.Orders, UserTab.History -> {
                        UserOrderHistoryScreen(
                            orders = orders,
                            allItems = allItems,
                            onOpenOrderDetail = { nav.navigate(OrderDetailDestination(it.id)) },
                            selectedTab = selectedTab,
                            onTabChange = { selectedTab = it }
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
                                orders = emptyList()
                                allItems.clear()
                                nav.navigate(LoginDestination) { popUpTo(MainDestination) { inclusive = true } }
                            },
                            onNotifications = { nav.navigate(NotificationsDestination) }
                        )
                    }
                    UserTab.Notifications -> {}
                }
            }


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
                                nav.navigate(MainDestination) { popUpTo(MainDestination) { inclusive = true } }
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
                                val productDetail = ProductDetailUi(
                                    id = product.id,
                                    name = product.name,
                                    description = product.descripcion,
                                    basePriceQ = product.priceLabel.filter { it.isDigit() }.toIntOrNull() ?: 25,
                                    imageUrl = product.imageUrl,
                                    parameters = listOf(
                                        ProductParameterUi("size", "Tamaño", listOf(
                                            ProductOptionUi("opt12", "Normal"),
                                            ProductOptionUi("opt16", "Deluxe", 4)
                                        ))
                                    ),
                                    serviceName = service.name
                                )
                                nav.navigate(ProductDestination(Json.encodeToString(productDetail), service.id))
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


            composable<ProductDestination> { backStackEntry ->
                val args = backStackEntry.toRoute<ProductDestination>()
                val product = Json.decodeFromString<ProductDetailUi>(args.productJson)

                UserProductScreen(
                    product = product,
                    onBack = { nav.popBackStack() },
                    onAddToOrder = { addedProduct ->
                        val existingOrder = orders.find { it.serviceName == addedProduct.serviceName }
                        if (existingOrder != null) {
                            orders = orders.map {
                                if (it.id == existingOrder.id)
                                    it.copy(totalQ = it.totalQ + addedProduct.basePriceQ, productCount = it.productCount + 1)
                                else it
                            }
                        } else {
                            orders = orders + OrderUi(
                                "order-${System.currentTimeMillis()}",
                                addedProduct.serviceName,
                                addedProduct.basePriceQ,
                                1,
                                "hoy - ${java.text.SimpleDateFormat("hh:mm a").format(java.util.Date())}",
                                "Pendiente"
                            )
                        }
                        allItems.add(UserOrderItemUi(
                            "${addedProduct.serviceName}-${System.currentTimeMillis()}",
                            addedProduct.name,
                            addedProduct.basePriceQ,
                            addedProduct.imageUrl,
                            1
                        ))
                        nav.navigate(ServiceDestination(args.serviceId))
                    }
                )
            }


            composable<OrderDetailDestination> { backStackEntry ->
                val order = orders.find { it.id == backStackEntry.toRoute<OrderDetailDestination>().orderId }
                if (order != null) {
                    val service = services.find { it.name == order.serviceName }
                    val restaurantItems = allItems.filter { it.id.startsWith(order.serviceName) }

                    val detail = UserOrderDetailUi(
                        id = order.id,
                        serviceName = order.serviceName,
                        status = order.status,
                        serviceLogoUrl = service?.bannerUrl ?: "",
                        items = restaurantItems
                    )

                    UserOrderDetailScreen(
                        data = detail,
                        onBack = { nav.popBackStack() }
                    )
                } else Text("Pedido no encontrado")
            }


            composable<NotificationsDestination> {
                NotificationsScreen(notifications, onBack = { nav.popBackStack() })
            }




            composable<ServiceOrdersDestination> {
                ServiceOrdersScreen(
                    negocio = serviceHeader.name ?: "Tu negocio",
                    tag = serviceHeader.tag,
                    logoUrl = serviceHeader.logoUrl,
                    orders = serviceOrders,
                    selectedTab = serviceSelectedTab,
                    onTabChange = { tab ->
                        serviceSelectedTab = tab
                        if (tab == SOrderTab.STORE) nav.navigate(ServiceStoreDestination)
                    },
                    onEditHeader = { },
                    onOpenOrder = { nav.navigate(ServiceOrderDetailDestination(it.id)) },
                    onLogout = {
                        userName = "Usuario"
                        userEmail = "usuario@uvg.edu.gt"
                        currentServiceInfo = null
                        nav.navigate(LoginDestination) {
                            popUpTo(ServiceOrdersDestination) { inclusive = true }
                        }
                    }
                )
            }


            composable<ServiceStoreDestination> {
                serviceSelectedTab = SOrderTab.STORE
                ServiceHomeScreen(
                    header = serviceHeader,
                    popular = servicePopular,
                    categories = serviceCategories,
                    selectedTab = SServiceTab.STORE,
                    onTabChange = { if (it == SServiceTab.ORDERS) nav.navigate(ServiceOrdersDestination) },
                    onAddCategory = { },
                    onAddPopularProduct = { nav.navigate(NewProductDestination) },
                    onOpenProduct = { nav.navigate(NewProductDestination) },
                    onEditHeader = { },
                    onLogout = {
                        userName = "Usuario"
                        userEmail = "usuario@uvg.edu.gt"
                        currentServiceInfo = null
                        nav.navigate(LoginDestination) {
                            popUpTo(ServiceStoreDestination) { inclusive = true }
                        }
                    }
                )
            }


            composable<ServiceOrderDetailDestination> { entry ->
                val args = entry.toRoute<ServiceOrderDetailDestination>()
                val order = serviceOrders.find { it.id == args.orderId }
                ServiceOrderDetailScreen(
                    customerName = order?.cliente ?: "Cliente",
                    statusText = order?.estado ?: "Pendiente",
                    items = serviceOrderItems[args.orderId] ?: emptyList(),
                    onBack = { nav.popBackStack() },
                    onComplete = {
                        serviceOrders = serviceOrders.map {
                            if (it.id == args.orderId) it.copy(estado = "Completado") else it
                        }
                        nav.popBackStack()
                    }
                )
            }


            composable<NewProductDestination> {
                NewProductScreen(onBack = { nav.popBackStack() })
            }
        }
    }
}