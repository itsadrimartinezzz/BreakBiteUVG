BreakBite UVG
Descripción
BreakBite UVG una aplicación móvil desarrollada en Kotlin. El objetivo de la app es optimizar la experiencia de compra de alimentos en la cafetería de la Universidad del Valle de Guatemala (UVG).
La app permite a los usuarios iniciar sesión para explorar menús de los diferentes restaurantes de la cafetería, realizar pedidos e irlos a recoger cuando estén listos.
El proyecto usa un patrón de View Model y utiliza room para la persistencia de datos local. La navegación se maneja a traves de Navigation Compose. También se integran funcionalidades simuladas de comunicación con servidores remotos a través de una API fake incluida dentro del proyecto.
Servicios
Estos son los servicios utilizados en la aplicación:
• Login:
• Tipo: Servicio simulado
• Descripción: Simula un sistema de autenticación de usuarios que valida credenciales y gestiona sesiones.
En esta fase aun se simula el inicio de sesion de la aplicación, sin embargo, en una etapa final de la misma se puede implementar un inicio de sesión auténtico con un API real. Actualmente, se maneja localmente a través de DataStore para conservar la sesión activa.
• Gestión de pedidos
• Tipo: Simulación
• Descripción: Simula la obtención y actualización de pedidos realizados por los usuarios, incluyendo estado ("En preparación", "Listo", "Completado") y tiempo estimado.
Permite pruebas de interfaz y flujo sin usar de momento conexión real a internet.
Se planifica sustituirla para que el servicio sea funcional.
• Servicios de restaurante (FakeApi.kt)
• Tipo: API Fake Local.
• Descripción: Devuelve información sobre los restaurantes disponibles (Barista, Café Gitane, Saul, Go Green, etc.), junto con los productos de cada uno (bebidas, comidas, postres).
Este servicio sirve como reemplazo temporal de una API real, cumpliendo con el requisito de llamada a internet dentro del entorno simulado.
• Llamada a Internet (Retrofit Example)
• Tipo: Simulada pero con ejemplo funcional.
• Descripción: Para cumplir con el requisito de realizar al menos una llamada real a internet, se integró Retrofit para acceder a un endpoint público de prueba. La llamada se usa para verificar conectividad y mostrar datos reales sin afectar las funcionalidades principales.
Librerías
• Room: para la persistencia de datos local (usuarios y pedidos).
• Retrofit: para realizar llamadas HTTP hacia APIs externas de prueba.
• Kotlinx Serialization: para serializar y deserializar objetos.
• Navigation Compose: para manejar la navegación entre pantallas.
• DataStore Preferences: para guardar el estado de sesión del usuario localmente.
• Coroutines y Flow: para operaciones asíncronas de red y base de datos.
• Material3 (Compose): para los componentes visuales con diseño de Material Design.
Estructura:
• data/ → Modelos de datos, clases FakeApi, ServiceUi, OrderUi, ProductUi.
• ui/screens/ → Pantallas principales para usuario y servicio (Login, SignUp, Home, Detalle de producto, etc.).
• ui/navegation/ → Definición de rutas, control de navegación y manejo de NavHost.
• repo/ → Repositorios para manejar lógica de negocio y conexión con la base de datos o servicios simulados.
• database/ → Configuración de Room (entidades, DAOs y base de datos principal).
Funcionalidades
• Registro e inicio de sesión de usuarios.
• Visualización de menús de restaurantes.
• Creación de pedidos.
• Pedidos completados y en curso.
• Navegación entre pantallas de usuario y servicio.
Tecnologías
• Lenguaje: Kotlin
• Framework: Jetpack Compose
• Arquitectura: MVVM
• Persistencia: Room y DataStore
• Diseño: Material Design
