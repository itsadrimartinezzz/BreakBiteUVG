package com.martinezzf.breakbitee.data

import com.martinezzf.breakbitee.ui.userScreens.SimpleProductUi
import com.martinezzf.breakbitee.ui.userScreens.SimpleCategoryUi

object FakeApi {

    fun getMenuForService(serviceId: String): Pair<List<SimpleProductUi>, List<SimpleCategoryUi>> {

        return when (serviceId) {

            "1" -> {
                val populares = listOf(
                    SimpleProductUi("1", "Latte", "Q25", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEzen5N3wvWNph_2_zzQQGKqNhLvRZCaweBg&s").apply { descripcion = "Café espresso con leche vaporizada y una capa cremosa de espuma." },
                    SimpleProductUi("2", "Capuccino", "Q23", "https://www.nespresso.com/coffee-blog/sites/default/files/2024-08/nespresso-recipes-CAPPUCCINO-BANANA-SESAME-SEEDS.jpg").apply { descripcion = "Café italiano con partes iguales de espresso, leche y espuma de leche." },
                    SimpleProductUi("3", "Matcha Latte", "Q27", "https://ucarecdn.com/6aaa3bb8-8a69-41a1-9f8a-7c9a7380c4d6/-/crop/4016x5425/0,296/-/preview/").apply { descripcion = "Leche vaporizada con té verde matcha japonés, cremosa y energizante." }
                )

                val categorias = listOf(
                    SimpleCategoryUi(
                        id = "c1",
                        name = "Bebidas Calientes",
                        products = listOf(
                            SimpleProductUi("p1", "Americano", "Q18", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYfE4fnUPGLsOY-6HVFZIXXR_8W4yT0bUKkQ&s").apply { descripcion = "Espresso diluido con agua caliente, sabor intenso y suave." },
                            SimpleProductUi("p2", "Espresso", "Q20", "https://sumatocoffee.com/cdn/shop/articles/espresso_d93cf1fb-0d4d-4da2-877f-c8226560ea4a.png?v=1758145494&width=640").apply { descripcion = "Café concentrado de sabor fuerte, servido en pequeñas porciones." },
                            SimpleProductUi("p3", "Mocha", "Q24", "https://gatherforbread.com/wp-content/uploads/2014/10/Dark-Chocolate-Mocha-Square.jpg").apply { descripcion = "Espresso con leche vaporizada y un toque de chocolate amargo." }
                        )
                    ),
                    SimpleCategoryUi(
                        id = "c2",
                        name = "Postres",
                        products = listOf(
                            SimpleProductUi("p4", "Muffin de chocolate", "Q15", "https://www.fifteenspatulas.com/wp-content/uploads/2020/08/Chocolate-Muffins-16.jpg").apply { descripcion = "Esponjoso muffin con trozos de chocolate derretido." },
                            SimpleProductUi("p5", "Pie de Elote", "Q22", "https://www.vvsupremo.com/wp-content/uploads/2015/11/900X570_Sweet-Corn-Pie.jpg").apply { descripcion = "Tradicional pie guatemalteco con el sabor dulce del maíz tierno." }
                        )
                    )
                )

                populares to categorias
            }

            "2" -> {
                val populares = listOf(
                    SimpleProductUi("1", "Cold Brew", "Q26", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkP2h1edPO_f266LRgJ6B9lDuJewhn3Omxkg&s").apply { descripcion = "Café infusionado en frío durante horas para un sabor suave y refrescante." },
                    SimpleProductUi("2", "Affogato", "Q30", "https://static01.nyt.com/images/2021/08/15/magazine/affogato/affogato-mediumSquareAt3X-v2.jpg").apply { descripcion = "Postre italiano de helado de vainilla bañado en espresso caliente." },
                    SimpleProductUi("3", "Iced Latte", "Q28", "https://www.livveganstrong.com/wp-content/uploads/2024/08/blueberry-iced-latte.jpg").apply { descripcion = "Latte frío con hielo y leche espumosa, ideal para días calurosos." }
                )

                val categorias = listOf(
                    SimpleCategoryUi(
                        id = "c1",
                        name = "Bebidas Frías",
                        products = listOf(
                            SimpleProductUi("p1", "Iced Americano", "Q24", "https://www.acouplecooks.com/wp-content/uploads/2022/01/Iced-Americano-008s.jpg").apply { descripcion = "Café americano servido con hielo, refrescante y ligero." },
                            SimpleProductUi("p2", "Mocha Frappé", "Q29", "https://foodservice502.com.gt/cdn/shop/files/Bebidas.Baseenpolvo.FrappesabormochaBigTrain.1.59kg.receta_c88f0b09-18a9-4681-8834-a85a84bcb400_700x700.jpg?v=1740757664").apply { descripcion = "Bebida helada con café, chocolate y crema batida." }
                        )
                    ),
                    SimpleCategoryUi(
                        id = "c2",
                        name = "Comidas",
                        products = listOf(
                            SimpleProductUi("p3", "Croissant", "Q20", "https://elparisino.com/wp-content/uploads/2020/07/IMG_0060-scaled.jpg").apply { descripcion = "Pan francés de hojaldre, crujiente por fuera y suave por dentro." },
                            SimpleProductUi("p4", "Sandwich de Pollo", "Q27", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7mNpkn7OcmvWTB9lDNvUj-EyZTFYS3PvzUw&s").apply { descripcion = "Pan artesanal con pechuga de pollo, lechuga y aderezo especial." }
                        )
                    )
                )

                populares to categorias
            }

            "3" -> {
                val populares = listOf(
                    SimpleProductUi("1", "Tarta de Queso", "Q28", "https://tartadequeso.pe/wp-content/uploads/2023/12/receta-tarta-de-queso.jpg").apply { descripcion = "Tarta cremosa con base de galleta y suave relleno de queso." },
                    SimpleProductUi("2", "Café Latte", "Q25", "https://gastrodome.com.my/wp-content/uploads/2025/04/cafe-latte-recipe-1745063911.jpg").apply { descripcion = "Café con leche vaporizada, de sabor balanceado y textura cremosa." },
                    SimpleProductUi("3", "Croissant de Almendra", "Q24", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXF8P6e8e-ZorNtyJnIDGJBOnZPVylJEQp7g&s").apply { descripcion = "Croissant relleno de crema de almendra, dulce y dorado." }
                )

                val categorias = listOf(
                    SimpleCategoryUi(
                        id = "c1",
                        name = "Postres",
                        products = listOf(
                            SimpleProductUi("p1", "Tartaleta de Frambuesa", "Q27", "https://img-global.cpcdn.com/recipes/ffab998ff0883dfb/1200x630cq80/photo.jpg").apply { descripcion = "Tartaleta rellena de crema pastelera y frambuesas frescas." },
                            SimpleProductUi("p2", "Brownie", "Q20", "https://mividaenundulce.com/wp-content/uploads/2018/05/dsc_0953.jpg?w=584").apply { descripcion = "Brownie húmedo de chocolate con textura densa y sabor intenso." }
                        )
                    ),
                    SimpleCategoryUi(
                        id = "c2",
                        name = "Bebidas",
                        products = listOf(
                            SimpleProductUi("p3", "Capuccino", "Q25", "https://gourmetdemexico.com.mx/wp-content/uploads/2024/08/cafe-capuccino-1024x678.jpg").apply { descripcion = "Espresso con leche y espuma espesa, un clásico italiano." },
                            SimpleProductUi("p4", "Chocolate Caliente", "Q23", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRE4-CtCbPsEWMl3jGuPwXkSaNc0TadMMYiTw&s").apply { descripcion = "Bebida caliente de cacao natural con leche cremosa." }
                        )
                    )
                )

                populares to categorias
            }

            "4" -> {
                val populares = listOf(
                    SimpleProductUi("1", "Ensalada César", "Q32", "https://www.gourmet.cl/wp-content/uploads/2016/09/EnsaladaCesar2.webp").apply { descripcion = "Clásica ensalada con pollo, lechuga, crutones y aderezo césar." },
                    SimpleProductUi("2", "Wrap Mediterráneo", "Q35", "https://calvo.es/wp-content/uploads/2020/04/08-Wrap-Mediterraneo-800x515-1.jpg").apply { descripcion = "Tortilla integral con vegetales, hummus y pollo sazonado." },
                    SimpleProductUi("3", "Burrito Vegano", "Q30", "https://storage.googleapis.com/avena-recipes-v2/2019/10/1571780047769.jpeg").apply { descripcion = "Burrito relleno de arroz integral, frijoles y verduras frescas." }
                )

                val categorias = listOf(
                    SimpleCategoryUi(
                        id = "c1",
                        name = "Ensaladas",
                        products = listOf(
                            SimpleProductUi("p1", "Ensalada Tropical", "Q34", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFJmLz6gL1ppImjwsKhRd1NnBCX8hlWtfuxA&s").apply { descripcion = "Mezcla de frutas, pollo y aderezo cítrico refrescante." },
                            SimpleProductUi("p2", "Ensalada con Quinoa", "Q36", "https://mojo.generalmills.com/api/public/content/RCngmFENdkS5zrHuS7yeVw_gmi_hi_res_jpeg.jpeg?v=93b2631f&t=16e3ce250f244648bef28c5949fb99ff").apply { descripcion = "Base de quinoa, aguacate, tomate y vinagreta balsámica." },
                            SimpleProductUi("p3", "Ensalada Power", "Q39", "https://lamanchega.com.uy/repo/img/27ensaladapower.jpg").apply { descripcion = "Ensalada energética con proteínas y vegetales frescos." }
                        )
                    ),
                    SimpleCategoryUi(
                        id = "c2",
                        name = "Wraps y Burritos",
                        products = listOf(
                            SimpleProductUi("p4", "Wrap de Pollo", "Q33", "https://resuelveconbimbo-com-v2-assets.s3.amazonaws.com/s3fs-public/2024-01/Banner%20Desktop_Wrap%20de%20Pollo.webp?VersionId=Gi5bWLFSfFnCTn80o5DrZEklPfhs8l3q").apply { descripcion = "Tortilla rellena de pollo, lechuga y salsa cremosa." },
                            SimpleProductUi("p5", "Burrito Mexicano", "Q35", "https://cdn0.recetasgratis.net/es/posts/9/4/3/burritos_mexicanos_de_carne_picada_55349_orig.jpg").apply { descripcion = "Burrito con frijoles, carne molida y pico de gallo." }
                        )
                    )
                )

                populares to categorias
            }

            "5" -> {
                val populares = listOf(
                    SimpleProductUi("1", "Panito de Jamón y Queso", "Q25", "https://storage.googleapis.com/avena-recipes-v2/2019/10/1571783463161.jpeg").apply { descripcion = "Clásico pan artesanal con jamón ahumado y queso derretido." },
                    SimpleProductUi("2", "Croissant de Pollo", "Q28", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9-fwQ6-O4Lxsr-HwiCByTEWAHEEQIIwHeow&s").apply { descripcion = "Croissant relleno de pollo sazonado y aderezo ligero." },
                    SimpleProductUi("3", "Panito Caprese", "Q30", "https://i0.wp.com/natirecetascaseras.com/wp-content/uploads/2025/04/gemini_generated_image_maw41nmaw41nmaw4.jpg?resize=750%2C750&ssl=1").apply { descripcion = "Pan ciabatta con tomate, queso mozzarella y pesto fresco." }
                )

                val categorias = listOf(
                    SimpleCategoryUi(
                        id = "c1",
                        name = "Panitos Clásicos",
                        products = listOf(
                            SimpleProductUi("p1", "Panito de Pavo", "Q27", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvEK9Gk0cEGwyzEGND_C8BLI3Je_JIFkcX7QdEzWU1HmyJYuzHu0i7bouZk7HjLDcX_hU&usqp=CAU").apply { descripcion = "Pan suave con pavo ahumado, queso y hojas de espinaca." },
                            SimpleProductUi("p2", "Panito de Atún", "Q26", "https://www.recetasnestle.com.mx/sites/default/files/srh_recipes/d8eba19e4f9a8f3889e91094522dfb72.jpg").apply { descripcion = "Pan tostado con mezcla cremosa de atún y vegetales." }
                        )
                    ),
                    SimpleCategoryUi(
                        id = "c2",
                        name = "Postres",
                        products = listOf(
                            SimpleProductUi("p3", "Tartaleta de Fresas", "Q20", "https://cdn7.kiwilimon.com/recetaimagen/14092/640x640/11845.jpg.jpg").apply { descripcion = "Tartaleta rellena de crema pastelera y fresas naturales." },
                            SimpleProductUi("p4", "Brownie de Chocolate", "Q18", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6seFMwtP-dbpIZPuk8n_3OQQ2Xvcs90e2uA&s").apply { descripcion = "Brownie suave y dulce, con sabor intenso a cacao." }
                        )
                    )
                )

                populares to categorias
            }

            "6" -> {
                val populares = listOf(
                    SimpleProductUi("1", "Mixta Clásica", "Q20", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLUgdp2o6-Tn-n-VNtQ13tRNKXvMaQ3gm_fg&s").apply { descripcion = "Mixta tradicional con repollo, mostaza y chorizo guatemalteco." },
                    SimpleProductUi("2", "Hot Dog Americano", "Q22", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaKqDVCWtGQzA2-d1WvRmTfPi0krczx2pwzQ&s").apply { descripcion = "Pan suave con salchicha americana, kétchup y queso fundido." },
                    SimpleProductUi("3", "Hamburguesa Especial", "Q35", "https://www.simplotfoods.com/_next/image?url=https%3A%2F%2Fimages.ctfassets.net%2F0dkgxhks0leg%2F2sDFsZYlCbQR7k6RrEQhCd%2F78f5176201067ff7fd6480adfcb80188%2FDouble_Dilly_Smashburger_with_fries.jpg%3Ffm%3Dwebp&w=3840&q=75").apply { descripcion = "Hamburguesa doble con queso, vegetales y papas fritas." }
                )

                val categorias = listOf(
                    SimpleCategoryUi(
                        id = "c1",
                        name = "Mixtas y Hot Dogs",
                        products = listOf(
                            SimpleProductUi("p1", "Mixta con Longaniza", "Q25", "https://aprende.guatemala.com/wp-content/uploads/2017/03/Receta-para-preparar-mixtas-guatemaltecas-2.jpg").apply { descripcion = "Mixta típica con longaniza, repollo y salsas tradicionales." },
                            SimpleProductUi("p2", "Hot Dog Mexicano", "Q26", "https://static01.nyt.com/images/2019/05/21/dining/kwr-mexican-hot-dogs/kwr-mexican-hot-dogs-videoSixteenByNineJumbo1600.jpg").apply { descripcion = "Hot dog con jalapeños, tocino y queso cheddar derretido." }
                        )
                    ),
                    SimpleCategoryUi(
                        id = "c2",
                        name = "Hamburguesas",
                        products = listOf(
                            SimpleProductUi("p3", "Hamburguesa Clásica", "Q33", "https://aprende.guatemala.com/wp-content/uploads/2021/05/Pasos-para-cocinar-hamburguesa-al-estilo-guatemalteco.png").apply { descripcion = "Hamburguesa sencilla con carne de res, lechuga y tomate." },
                            SimpleProductUi("p4", "Hamburguesa Doble Queso", "Q38", "https://static.wixstatic.com/media/29cc8e_aaad1f9b690b4176a0cef213b971f787~mv2.jpg/v1/fill/w_568,h_378,al_c,q_80,usm_0.66_1.00_0.01,enc_avif,quality_auto/29cc8e_aaad1f9b690b4176a0cef213b971f787~mv2.jpg").apply { descripcion = "Doble carne, doble queso y pan artesanal recién tostado." }
                        )
                    )
                )

                populares to categorias
            }

            "7" -> {
                val populares = listOf(
                    SimpleProductUi("1", "Panini Avocado", "Q45", "https://www.lecafeguatemala.com/_uploads/menus/imgs/full/693-20230505143758.jpg").apply { descripcion = "Panini con aguacate, pollo y queso derretido al grill." },
                    SimpleProductUi("2", "Hot Dog", "Q50", "https://assets.tmecosys.com/image/upload/t_web_rdp_recipe_584x480_1_5x/img/recipe/ras/Assets/5cf6c117f5c41319d85be7490cbb0e47/Derivates/0fa2242bd92c2f55248c9d38bdbca4b5d543a220.jpg").apply { descripcion = "Hot dog gourmet con salsa especial y cebolla caramelizada." },
                    SimpleProductUi("3", "Hamburguesa", "Q55", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSddjpE64jVcMqw9x270i2vfbklSHIt-LzP7w&s").apply { descripcion = "Hamburguesa premium con ingredientes seleccionados." }
                )

                val categorias = listOf(
                    SimpleCategoryUi(
                        id = "c1",
                        name = "Sushi Clásico",
                        products = listOf(
                            SimpleProductUi("p1", "Sushi roll California", "Q42", "https://norecipes.com/wp-content/uploads/2019/12/best-california-roll-004.jpg").apply { descripcion = "Roll de sushi con aguacate, pepino y cangrejo." },
                            SimpleProductUi("p2", "Hot roll", "Q48", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7OHY4oSwm1_HincoZApNwwF3BK2RZmQdFbg&s").apply { descripcion = "Roll empanizado con queso crema y salsa de anguila." }
                        )
                    ),
                    SimpleCategoryUi(
                        id = "c2",
                        name = "Combos",
                        products = listOf(
                            SimpleProductUi("p3", "Panini + Gaseosa", "Q50", "https://thumbs.dreamstime.com/b/club-sandwich-y-cola-s%C3%A1ndwich-vaso-de-bebida-con-hielo-comida-r%C3%A1pida-para-llevar-220300534.jpg").apply { descripcion = "Combo con panini artesanal y bebida fría incluida." },
                            SimpleProductUi("p4", "Hamburguesa + Papas Fritas + Gaseosa", "Q120", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRR0jYBFR1nwUmfKfeLut6fLD3RFjApSXO-SQ&s").apply { descripcion = "Combo completo con hamburguesa premium, papas y bebida." }
                        )
                    )
                )

                populares to categorias
            }

            else -> emptyList<SimpleProductUi>() to emptyList()
        }
    }
}
