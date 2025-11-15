package com.martinezzf.breakbitee.data

import com.martinezzf.breakbitee.ui.userScreens.SimpleCategoryUi
import com.martinezzf.breakbitee.ui.userScreens.SimpleProductUi

object FakeApi {

    /**
     * Devuelve un par con productos populares y categorías por servicio.
     * El formato es: Pair<List<SimpleProductUi>, List<SimpleCategoryUi>>
     */
    fun getMenuForService(serviceId: String): Pair<List<SimpleProductUi>, List<SimpleCategoryUi>> {
        return when (serviceId) {

            // ======== Café Barista ========
            "1" -> {
                val populares = listOf(
                    SimpleProductUi("b1", "Latte", "Q25", "https://i.imgur.com/MQyQxKr.png", "Café con leche espumosa."),
                    SimpleProductUi("b2", "Capuccino", "Q27", "https://i.imgur.com/NzHh5Ep.png", "Café cremoso con espuma."),
                    SimpleProductUi("b3", "Americano", "Q20", "https://i.imgur.com/ojh1dVq.png", "Café negro tradicional.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "bebidas", "Bebidas calientes",
                        listOf(
                            SimpleProductUi("b4", "Mocha", "Q28", "https://i.imgur.com/sBiy5Xf.png", "Café con chocolate."),
                            SimpleProductUi("b5", "Matcha Latte", "Q30", "https://i.imgur.com/22wYlQ8.png", "Té verde con leche.")
                        )
                    ),
                    SimpleCategoryUi(
                        "comidas", "Comidas",
                        listOf(
                            SimpleProductUi("b6", "Croissant jamón y queso", "Q22", "https://i.imgur.com/BGQ3YpU.png", "Horneado fresco."),
                            SimpleProductUi("b7", "Panini pollo pesto", "Q28", "https://i.imgur.com/fq8yXyJ.png", "Panini con salsa pesto.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            // ======== & Cafe ========
            "2" -> {
                val populares = listOf(
                    SimpleProductUi("c1", "Café negro", "Q20", "https://i.imgur.com/ojh1dVq.png", "Café simple y fuerte."),
                    SimpleProductUi("c2", "Iced Latte", "Q26", "https://i.imgur.com/MQyQxKr.png", "Café frío con leche."),
                    SimpleProductUi("c3", "Chai Latte", "Q28", "https://i.imgur.com/bbZ3nCt.png", "Infusión de especias y leche.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "dulces", "Postres",
                        listOf(
                            SimpleProductUi("c4", "Muffin de chocolate", "Q18", "https://i.imgur.com/8f0pMIr.png", "Suave y dulce."),
                            SimpleProductUi("c5", "Cheesecake", "Q25", "https://i.imgur.com/J4k3xgZ.png", "Pastel de queso clásico.")
                        )
                    ),
                    SimpleCategoryUi(
                        "bebidas", "Bebidas frías",
                        listOf(
                            SimpleProductUi("c6", "Matcha Frappe", "Q29", "https://i.imgur.com/22wYlQ8.png", "Refrescante y cremoso."),
                            SimpleProductUi("c7", "Cold Brew", "Q24", "https://i.imgur.com/ojh1dVq.png", "Café reposado en frío.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            // ======== Gitane ========
            "3" -> {
                val populares = listOf(
                    SimpleProductUi("g1", "Smoothie Tropical", "Q24", "https://i.imgur.com/xm7aUTL.png", "Frutas naturales."),
                    SimpleProductUi("g2", "Croissant dulce", "Q18", "https://i.imgur.com/BGQ3YpU.png", "Fino y hojaldrado.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "desayunos", "Desayunos",
                        listOf(
                            SimpleProductUi("g3", "Tostadas francesas", "Q25", "https://i.imgur.com/BR3OGcQ.png", "Con miel y canela."),
                            SimpleProductUi("g4", "Avena Gitane", "Q22", "https://i.imgur.com/nV6xLUu.png", "Saludable y ligera.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            // ======== Go Green ========
            "4" -> {
                val populares = listOf(
                    SimpleProductUi("gg1", "Wrap veggie", "Q28", "https://i.imgur.com/nV6xLUu.png", "Wrap saludable con hummus."),
                    SimpleProductUi("gg2", "Smoothie verde", "Q26", "https://i.imgur.com/xm7aUTL.png", "Frutas y espinaca.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "ensaladas", "Ensaladas",
                        listOf(
                            SimpleProductUi("gg3", "Ensalada César", "Q30", "https://i.imgur.com/22wYlQ8.png", "Con pollo y aderezo."),
                            SimpleProductUi("gg4", "Ensalada Vegana", "Q27", "https://i.imgur.com/sBiy5Xf.png", "Con garbanzos y aguacate.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            // ======== Panitos y algo más ========
            "5" -> {
                val populares = listOf(
                    SimpleProductUi("p1", "Sandwich mixto", "Q22", "https://i.imgur.com/fq8yXyJ.png", "Clásico jamón y queso."),
                    SimpleProductUi("p2", "Croissant dulce", "Q18", "https://i.imgur.com/BGQ3YpU.png", "Con azúcar glaseada.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "bebidas", "Bebidas calientes",
                        listOf(
                            SimpleProductUi("p3", "Café con leche", "Q20", "https://i.imgur.com/MQyQxKr.png", "Suave y espumoso."),
                            SimpleProductUi("p4", "Chocolate caliente", "Q22", "https://i.imgur.com/sBiy5Xf.png", "Dulce y espeso.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            // ======== Mixtas Frankfurt ========
            "6" -> {
                val populares = listOf(
                    SimpleProductUi("m1", "Mixta tradicional", "Q20", "https://i.imgur.com/0Yk2x4D.png", "Clásica con repollo y chiles."),
                    SimpleProductUi("m2", "Mixta especial", "Q25", "https://i.imgur.com/KIE6bQZ.png", "Con longaniza y aguacate.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "especiales", "Especiales del día",
                        listOf(
                            SimpleProductUi("m3", "Hot dog alemán", "Q28", "https://i.imgur.com/hKeNPaS.png", "Salchicha estilo Frankfurt."),
                            SimpleProductUi("m4", "Hamburguesa BBQ", "Q32", "https://i.imgur.com/tlZ5RjZ.png", "Con salsa especial.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            // ======== Golden Harvest ========
            "7" -> {
                val populares = listOf(
                    SimpleProductUi("gh1", "Bowl asiático", "Q35", "https://i.imgur.com/22wYlQ8.png", "Con vegetales y arroz."),
                    SimpleProductUi("gh2", "Té jazmín", "Q18", "https://i.imgur.com/xm7aUTL.png", "Suave y aromático.")
                )
                val categorias = listOf(
                    SimpleCategoryUi(
                        "platos", "Platos fuertes",
                        listOf(
                            SimpleProductUi("gh3", "Pollo teriyaki", "Q38", "https://i.imgur.com/sBiy5Xf.png", "Con salsa oriental."),
                            SimpleProductUi("gh4", "Tofu salteado", "Q33", "https://i.imgur.com/nV6xLUu.png", "Con salsa de soya ligera.")
                        )
                    )
                )
                Pair(populares, categorias)
            }

            // ======== Default ========
            else -> Pair(emptyList(), emptyList())
        }
    }
}
