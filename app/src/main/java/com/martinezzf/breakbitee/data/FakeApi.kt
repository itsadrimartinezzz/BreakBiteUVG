/**
 * Fake api: actua como una API falsa interna para la aplicacion. Se utiliza principalmente para simular un servidor real sin el uso de internet utilizando stateFlow para que la UI se actualice.
 * 1. Principales funciones: Guardar pedidos por restaurante (barista, &cafe, gitane, go green, panitos, mixtas frankfurt, golden harvest)
 * 2. Agregar pedidos
 * 3. Devuelve los pedidos de un restaurante
 * 4. Devuelve menu completo por servicio
*/
package com.martinezzf.breakbitee.data

//Import
import com.martinezzf.breakbitee.ui.userScreens.SimpleCategoryUi
import com.martinezzf.breakbitee.ui.userScreens.SimpleProductUi
import com.martinezzf.breakbitee.ui.userScreens.UserNotification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object FakeApi {


    //Utiliza MutableStateFlow para almacenar los pedidos de ServiceOrderUi que es la perspectiva de servicio del restaurante.
    private val barista = MutableStateFlow<List<ServiceOrderUi>>(emptyList())
    private val cafe = MutableStateFlow<List<ServiceOrderUi>>(emptyList())
    private val gitane = MutableStateFlow<List<ServiceOrderUi>>(emptyList())
    private val goGreen = MutableStateFlow<List<ServiceOrderUi>>(emptyList())
    private val panitos = MutableStateFlow<List<ServiceOrderUi>>(emptyList())
    private val mixtas = MutableStateFlow<List<ServiceOrderUi>>(emptyList())
    private val golden = MutableStateFlow<List<ServiceOrderUi>>(emptyList())

    //La funcion recibe un restaurante y un pedido. Agregando el pedido a la lista correspondiente dentro del FakeApi.
    fun enviarPedido(restaurante: String, pedido: ServiceOrderUi) {
        //Utiliza when para determinar a que restaurante se debe mandar.
        when (restaurante.lowercase()) {
            "barista" -> barista.value = barista.value + pedido
            "& cafe", "cafe" -> cafe.value = cafe.value + pedido
            "gitane" -> gitane.value = gitane.value + pedido
            "go green", "gogreen" -> goGreen.value = goGreen.value + pedido
            "panitos" -> panitos.value = panitos.value + pedido
            "mixtas" -> mixtas.value = mixtas.value + pedido
            "golden", "golden harvest" -> golden.value = golden.value + pedido
        }
    }

    //Regresa la lista de pedidos del restaurante en donde el usuario pide.
    fun getPedidos(restaurante: String): StateFlow<List<ServiceOrderUi>> {
        return when (restaurante.lowercase()) {
            "barista" -> barista
            "& cafe", "cafe" -> cafe
            "gitane" -> gitane
            "go green", "gogreen" -> goGreen
            "panitos" -> panitos
            "mixtas" -> mixtas
            "golden", "golden harvest" -> golden
            else -> MutableStateFlow(emptyList())
        }
    }

    //Notificaciones de usuario

        private val _userNotifications =
            MutableStateFlow<List<UserNotification>>(emptyList())

        /**
         * Devuelve todas las notificaciones del usuario como StateFlow,
         * permitiendo que la UI se actualice automáticamente.
         */
        fun getUserNotifications(): StateFlow<List<UserNotification>> = _userNotifications

        /**
         * Agrega una nueva notificación al usuario.
         */
        fun sendNotificationToUser(notification: UserNotification) {
            _userNotifications.value = _userNotifications.value + notification
        }


    /**
     * Devuelve el menu del restaurante segun su serviceId.
     * Tiene la estructura de Pair( listaDeProductosPopulares, listaDeCategoriasConSusProductos)
     */
    fun getMenuForService(serviceId: String): Pair<List<SimpleProductUi>, List<SimpleCategoryUi>> {
        return when (serviceId) {

            // ======== Café Barista ========
            "1" -> {
                val populares = listOf(
                    SimpleProductUi("b1", "Latte", "Q25", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSf0RgSexAbiZiUTn5Q5IcdjKTgbmSrGqXpZg&s", "Café con leche espumosa."),
                    SimpleProductUi("b2", "Capuccino", "Q27", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsqEQVhGGv8qcJiEwkyTkVuaM1lV0HqqvXww&s", "Café cremoso con espuma."),
                    SimpleProductUi("b3", "Americano", "Q20", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzAn7sFMZCSR1VWT-9TPvJhlVghgu0keOYyg&s", "Café negro tradicional.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "bebidas", "Bebidas calientes",
                        listOf(
                            SimpleProductUi("b4", "Mocha", "Q28", "https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/the_perfect_mocha_coffee_29100_16x9.jpg", "Café con chocolate."),
                            SimpleProductUi("b5", "Matcha Latte", "Q30", "https://www.justonecookbook.com/wp-content/uploads/2022/12/Matcha-Latte-4598-I-1-500x375.jpg", "Té verde con leche.")
                        )
                    ),
                    SimpleCategoryUi(
                        "comidas", "Comidas",
                        listOf(
                            SimpleProductUi("b6", "Croissant jamón y queso", "Q22", "https://mejorconsalud.as.com/wp-content/uploads/2015/07/croissants-de-jam%C3%B3n-y-queso.jpg", "Horneado fresco."),
                            SimpleProductUi("b7", "Panini pollo pesto", "Q28", "https://bydash.com/cdn/shop/articles/20241003170904-chicken-2c-20mozzarella-2c-20-26-20kale-20pesto-20panini.jpg?v=1750275694", "Panini con salsa pesto.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            // ======== & Cafe ========
            "2" -> {
                val populares = listOf(
                    SimpleProductUi("c1", "Café negro", "Q20", "https://fnb.coffee/wp-content/uploads/2024/10/Americano-Coffee.webp", "Café simple y fuerte."),
                    SimpleProductUi("c2", "Iced Latte", "Q26", "https://www.livveganstrong.com/wp-content/uploads/2024/08/blueberry-iced-latte.jpg", "Café frío con leche."),
                    SimpleProductUi("c3", "Chai Latte", "Q28", "https://cdn.loveandlemons.com/wp-content/uploads/2025/01/chai-latte.jpg", "Infusión de especias y leche.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "dulces", "Postres",
                        listOf(
                            SimpleProductUi("c4", "Muffin de chocolate", "Q18", "https://www.cocinadelirante.com/sites/default/files/images/2020/09/muffin-de-chocolate-abuelita.jpg", "Suave y dulce."),
                            SimpleProductUi("c5", "Cheesecake", "Q25", "https://i.blogs.es/550ed3/cheesecake/450_1000.jpg", "Pastel de queso clásico.")
                        )
                    ),
                    SimpleCategoryUi(
                        "bebidas", "Bebidas frías",
                        listOf(
                            SimpleProductUi("c6", "Matcha Frappe", "Q29", "https://nioteas.es/cdn/shop/articles/20240923154428-matcha-20frappuccino-20recipe_17ea98d4-22b1-43c7-b1ea-59e8d21d2e97.jpg?v=1755848723", "Refrescante y cremoso."),
                            SimpleProductUi("c7", "Cold Brew", "Q24", "https://www.simplyrecipes.com/thmb/7zYXgL4vpOhXfa04v7_vPO4Dv84=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Simply-Recipes-Cold-Brew-Coffee-LEAD-6-896c6872ac3e421ca4d88f29b528b349.jpg", "Café reposado en frío.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            // ======== Gitane ========
            "3" -> {
                val populares = listOf(
                    SimpleProductUi("g1", "Smoothie Tropical", "Q24", "https://www.jessicagavin.com/wp-content/uploads/2020/06/tropical-smoothie-5-1200.jpg", "Frutas naturales."),
                    SimpleProductUi("g2", "Salchipapa", "Q16", "https://recetinas.com/wp-content/uploads/2017/10/salchipapas.jpg", "Salchicha con papa.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "desayunos", "Desayunos",
                        listOf(
                            SimpleProductUi("g3", "Tostadas francesas", "Q25", "https://embed.widencdn.net/img/mccormick/l07tbd4l9q/800x800px/tostadas_a_la_francesa.jpg?crop=true&q=80&color=ffffffff&u=qwwekl", "Con miel y canela."),
                            SimpleProductUi("g4", "Panini de pollo", "Q22", "https://cocinamia.com.mx/wp-content/uploads/2020/02/a-63-1.png", "Saludable y ligera.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            // ======== Go Green ========
            "4" -> {
                val populares = listOf(
                    SimpleProductUi("gg1", "Wrap veggie", "Q28", "https://www.plantperks.com/wp-content/uploads/2020/02/IMG_0198-Edit.jpg", "Wrap saludable con hummus."),
                    SimpleProductUi("gg2", "Smoothie verde", "Q26", "https://www.vitamixespana.com/recetas/wp-content/uploads/2018/04/smoothie-verde-vitamix.jpg", "Frutas y espinaca.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "ensaladas", "Ensaladas",
                        listOf(
                            SimpleProductUi("gg3", "Ensalada César", "Q30", "https://www.goodnes.com/sites/g/files/jgfbjl321/files/srh_recipes/755f697272cbcdc6e5df2adb44b1b705.jpg", "Con pollo y aderezo."),
                            SimpleProductUi("gg4", "Ensalada Vegana", "Q27", "https://danzadefogones.com/wp-content/uploads/2019/02/Ensalada-italiana-vegana-5.jpg", "Con garbanzos y aguacate.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            // ======== Panitos ========
            "5" -> {
                val populares = listOf(
                    SimpleProductUi("p1", "Sandwich mixto", "Q22", "https://cdn0.uncomo.com/es/posts/0/6/9/como_hacer_un_sandwich_mixto_33960_orig.jpg", "Clásico jamón y queso."),
                    SimpleProductUi("p2", "Croissant dulce", "Q18", "https://i.ytimg.com/vi/6iCISBibmLw/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLAhYTKJIrw3jm69kf1Yoavdu2tkAw", "Con azúcar glaseada.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "bebidas", "Bebidas calientes",
                        listOf(
                            SimpleProductUi("p3", "Café con leche", "Q20", "https://www.recetasnestle.com.ec/sites/default/files/styles/recipe_detail_desktop_new/public/srh_recipes/ee22eb5c9024e4db71a9d4f587b18038.png?itok=K_r5LmdS", "Suave y espumoso."),
                            SimpleProductUi("p4", "Chocolate caliente", "Q22", "https://www.cocinadelirante.com/sites/default/files/images/2024/11/prepara-chocolate-caliente-con-bombones-y-alivia-la-tos.jpg", "Dulce y espeso.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            // ======== Mixtas ========
            "6" -> {
                val populares = listOf(
                    SimpleProductUi("m1", "Mixta tradicional", "Q20", "https://aprende.guatemala.com/wp-content/uploads/2017/03/Receta-para-preparar-mixtas-guatemaltecas-2.jpg", "Clásica con repollo y chiles."),
                    SimpleProductUi("m2", "Mixta especial", "Q25", "https://www.prensalibre.com/wp-content/uploads/2019/01/247f7bb6-3691-420a-8746-a327a23f32ed.jpg?quality=52", "Con longaniza y aguacate.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "especiales", "Especiales del día",
                        listOf(
                            SimpleProductUi("m3", "Hot dog alemán", "Q28", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSigGKTkOXUr1EVsV7SkYNQJVaf-9hus0QA-w&s", "Salchicha estilo Frankfurt."),
                            SimpleProductUi("m4", "Hamburguesa BBQ", "Q32", "https://www.recetasnestlecam.com/sites/default/files/srh_recipes/74e1a2dfe688f08eedf86a3711c8e4fb.png", "Con salsa especial.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            // ======== Golden ========
            "7" -> {
                val populares = listOf(
                    SimpleProductUi("gh1", "Hamburguesa", "Q35", "https://aprende.guatemala.com/wp-content/uploads/2021/05/Pasos-para-cocinar-hamburguesa-al-estilo-guatemalteco.png", "Hamburguesa clasica."),
                    SimpleProductUi("gh2", "Jamaica", "Q18", "https://www.infobae.com/resizer/v2/IDNEPYYXRJBFHBLLZZ5BO5OJDY.jpg?auth=dad66630ffc1b14e481b147e19b61f8c5600fa5bc65202fd671c31ab759f8981&smart=true&width=1024&height=512&quality=85", "Dulce y aromático.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "platos", "Platos fuertes",
                        listOf(
                            SimpleProductUi("gh3", "Sushi", "Q38", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSM8DUrVC_O8jh6J9Za0QiH9rqKnnI6a3hqKw&s", "Con salsa oriental."),
                            SimpleProductUi("gh4", "Club Sandwich", "Q33", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQE2dZQ2TkMsyVqiuXqFzhQGPRD3RZeNEeHuA&s", "Con pollo y vegetales frescos.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            else -> Pair(emptyList(), emptyList())
        }
    }
}
