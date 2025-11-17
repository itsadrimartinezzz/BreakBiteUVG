#  BreakBite UVG 

##  Descripción  
**BreakBite UVG** es una aplicación móvil desarrollada en **Kotlin** con **Jetpack Compose**.  
Su objetivo es **optimizar la experiencia de compra de alimentos en la cafetería de la Universidad del Valle de Guatemala (UVG)**.  

La app permite a los usuarios:
- Iniciar sesión dual (estudiantes con correo UVG y servicios/restaurantes)
- Explorar los menús de los diferentes restaurantes con imágenes reales
- Realizar pedidos personalizados con opciones y extras configurables
- Recibir **notificaciones en tiempo real** sobre el estado de los pedidos
- Ver historial completo de pedidos con detalles
- Gestionar pedidos desde la perspectiva del restaurante
- Alternar entre **modo claro y oscuro**
- Gestionar el menú y productos desde el panel del restaurante

El proyecto sigue el patrón **MVVM (Model-View-ViewModel)** y utiliza **Navigation Compose** para la navegación entre pantallas.  
Incluye servicios simulados que emulan la comunicación con un servidor remoto mediante una **API fake local** con **StateFlow** para actualizaciones en tiempo real.

---

##  Usuarios de Prueba

| Restaurante | Usuario | Contraseña |
|--------------|----------|-------------|
|  Barista | barista | **barista123** |
|  Go Green | gogreen | **gogreen123** |
|  &Café | elcafe | **elcafe123** |
|  Gitane | gitane | **gitane123** |
|  Panitos | panitos | **panitos123** |
|  Golden | golden | **golden123** |
|  Frankfurt | frankfurt | **frankfurt123** |

>  Usa estos usuarios para iniciar sesión en la aplicación durante las pruebas.

---

##  Funcionalidades Principales

###  Login Multi-Rol
- **Tipo:** Servicio simulado  
- **Descripción:** Sistema de autenticación dual que soporta:
  - **Estudiantes**: Login con correo UVG (@uvg.edu.gt)
  - **Servicios**: Login con nombre del restaurante y contraseña
- Cada servicio tiene acceso a su panel personalizado con:
  - Logo y branding específico
  - Menú propio de productos con imágenes
  - Gestión de pedidos en tiempo real

###  Sistema de Notificaciones en Tiempo Real
- **Tipo:** Sistema de notificaciones push simulado
- **Descripción:** Los usuarios reciben notificaciones automáticas cuando:
  - El restaurante recibe su pedido
  -  El pedido está listo para recoger
- Almacenamiento persistente de notificaciones
- Acceso desde el perfil del usuario
- Integración con **FakeApi** mediante **StateFlow**

###  Gestión de Pedidos por Servicio
- **Tipo:** Simulación multi-servicio  
- **Descripción:** Sistema independiente de pedidos donde cada restaurante maneja:
  - Estados: `"Pendiente"`, `"En preparación"`, `"Completado"`
  - Lista de productos con imágenes y detalles
  - Vista detallada de cada pedido
  - Actualización de estados que dispara notificaciones
- Permite completar pedidos y actualizar estados en tiempo real

###  API de Restaurantes (`FakeApi.kt`)
- **Tipo:** API Fake Local con StateFlow
- **Descripción:** Devuelve información dinámica sobre:
  - 7 restaurantes disponibles con logos reales y menús completos
  - Productos categorizados con imágenes (bebidas, comidas, postres)
  - Información específica por servicio (nombre, ubicación, tiempo de espera)
  - Sistema de notificaciones integrado
- Sirve como reemplazo temporal de una API real
- **Actualización en tiempo real** mediante `StateFlow`

###  Modo Oscuro
- **Descripción:** Los usuarios pueden alternar entre modo claro y oscuro
- Configuración persistente usando `rememberSaveable`
- Afecta toda la interfaz de la aplicación
- Disponible desde el perfil del usuario

---

##  Funcionalidades por Rol

###  Vista Estudiante
-  **Explorar servicios disponibles** con imágenes y logos reales
-  **Ver menús completos** con imágenes de productos y descripciones
-  **Personalizar pedidos** (tamaños, extras con costos adicionales)
-  **Recibir notificaciones** sobre estado de pedidos
-  **Ver notificaciones** desde el perfil
-  **Historial de pedidos** con detalles completos y precios
-  **Detalle de pedido** individual con lista de productos
-  **Modo oscuro** configurable

###  Vista Servicio (Restaurante)
-  **Panel personalizado** con logo y branding del restaurante
-  **Gestión de pedidos en tiempo real** con filtrado por restaurante
-  **Visualización de pedidos** con estados (Pendiente, En preparación, Completado)
-  **Vista detallada de pedido** con información del cliente y productos
-  **Cambio de estados** de pedidos que envía notificaciones
-  **Vista de tienda** para gestionar menú (en desarrollo)
-  **Agregar nuevo producto** (interfaz lista, lógica en desarrollo)
-  **Cerrar sesión** seguro con confirmación

---

##  Estructura del Proyecto

```
app/
├── data/
│   ├── FakeApi.kt              # API simulada con StateFlow para pedidos y notificaciones
│   ├── ServiceUi.kt            # Modelo de servicio/restaurante
│   ├── OrderUi.kt              # Modelo de pedido (estudiante)
│   ├── ServiceOrderUi.kt       # Modelo de pedido (servicio)
│   ├── ProductUi.kt            # Modelos de producto (detalle, parámetros, opciones)
│   ├── OrderItemUi.kt          # Modelo de item dentro de un pedido
│   └── UserOrderItemUi.kt      # Modelo de item de pedido del usuario
├── ui/
│   ├── screens/
│   │   ├── LoginScreen.kt      # Pantalla de login dual
│   │   ├── SignUpScreen.kt     # Registro (UVG y servicios)
│   │   └── components/         # Componentes reutilizables (botones, inputs, etc.)
│   ├── userScreens/            # Pantallas de estudiante
│   │   ├── UserHomeScreen.kt           # Explorar restaurantes
│   │   ├── UserServiceScreen.kt        # Ver menú del restaurante
│   │   ├── UserProductScreen.kt        # Detalle de producto
│   │   ├── UserOrderHistoryScreen.kt   # Historial de pedidos
│   │   ├── UserOrderDetailScreen.kt    # Detalle de un pedido
│   │   ├── UserProfileScreen.kt        # Perfil y configuración
│   │   ├── NotificationsScreen.kt      # Ver notificaciones
│   │   └── OrderCompletedScreen.kt     # Confirmación de pedido
│   ├── serviceScreens/         # Pantallas de servicio
│   │   ├── ServiceModels.kt            # Modelos compartidos
│   │   ├── ServiceOrdersScreen.kt      # Lista de pedidos
│   │   ├── ServiceOrderDetailScreen.kt # Detalle de pedido
│   │   ├── ServiceHomeScreen.kt        # Gestión de tienda
│   │   ├── NewProductScreen.kt         # Agregar producto nuevo
│   │   └── ServiceOrdersScreenWrapper.kt # Wrapper con StateFlow
│   ├── navegation/
│   │   ├── navegation.kt       # Sistema de navegación multi-rol
│   │   ├── Destination.kt      # Definición de rutas
│   │   ├── UserTab.kt          # Tabs del usuario
│   │   └── LocalAllItems.kt    # Contexto global de items
│   └── theme/
│       ├── Color.kt            # Paleta de colores
│       ├── Theme.kt            # Configuración de tema
│       └── Type.kt             # Tipografía
└── MainActivity.kt             # Punto de entrada con modo oscuro
```

---

##  Librerías Utilizadas

###  UI & Navegación
```gradle
// Jetpack Compose - UI declarativa
implementation("androidx.compose.material3:material3:1.1.2")
implementation("androidx.compose.material:material-icons-extended:1.7.8")

// Navigation Compose - Navegación type-safe
implementation("androidx.navigation:navigation-compose:2.9.5")
```

###  Carga de Imágenes
```gradle
// Coil - Carga de imágenes desde URLs
implementation("io.coil-kt:coil-compose:2.7.0")
```

###  Serialización
```gradle
// Kotlinx Serialization - Pasar objetos entre pantallas
implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
```

###  Arquitectura
```gradle
// Lifecycle & ViewModel - Gestión de estado
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.4")

// Core Android KTX
implementation("androidx.core:core-ktx:1.17.0")
```

---

##  Tecnologías

| Categoría | Herramienta | Uso en el Proyecto |
|------------|--------------|-------------------|
| **Lenguaje** | Kotlin 2.2.20 | Lenguaje principal |
| **Framework** | Jetpack Compose | UI declarativa moderna |
| **Arquitectura** | MVVM | Separación de capas |
| **Navegación** | Navigation Compose | Rutas type-safe con serialización |
| **Imágenes** | Coil 2.7.0 | Carga asíncrona desde URLs |
| **Serialización** | Kotlinx Serialization | Paso de datos entre pantallas |
| **Diseño** | Material Design 3 | Interfaz consistente |
| **Estado Reactivo** | StateFlow | Actualizaciones en tiempo real |

---

##  Características Destacadas

###  Sistema Multi-Rol
- Login dual: estudiantes (email UVG) y servicios (nombre del restaurante)
- Navegación independiente según el rol
- Persistencia de sesión con `rememberSaveable`

###  Notificaciones en Tiempo Real
- Sistema de notificaciones push simulado
- Notificación automática cuando el restaurante recibe un pedido
- Notificación cuando el pedido está listo para recoger
- Almacenamiento persistente en `FakeApi`
- Acceso desde el perfil del usuario

###  Gestión de Pedidos Dinámica
- Los pedidos se envían automáticamente al restaurante correspondiente
- Actualización en tiempo real mediante `StateFlow`
- Filtrado automático de pedidos por restaurante
- Estados de pedido que disparan notificaciones

###  Interfaz Adaptativa
- Modo claro y oscuro
- Diseño responsive con Jetpack Compose
- Imágenes reales de productos y restaurantes
- Animaciones y transiciones fluidas

###  Personalización de Productos
- Sistema de parámetros y opciones
- Costos adicionales configurables
- Previsualización de precio total
- Cantidad ajustable

---

##  Flujo de Usuario

### Estudiante
1. **Login** con correo @uvg.edu.gt
2. **Explorar** restaurantes disponibles con búsqueda
3. **Seleccionar** un restaurante para ver su menú
4. **Elegir productos** y personalizarlos
5. **Agregar a la orden** múltiples productos
6. **Completar pedido** → Confirmación visual
7. **Recibir notificación** de que el pedido fue recibido
8. **Recibir notificación** cuando el pedido esté listo
9. **Ver historial** de pedidos con detalles
10. **Consultar notificaciones** desde el perfil

### Servicio/Restaurante
1. **Login** con nombre de restaurante y contraseña
2. **Ver pedidos** filtrados por su restaurante
3. **Abrir detalle** de un pedido específico
4. **Marcar como completado** → Envía notificación al usuario
5. **Gestionar tienda** (en desarrollo)
6. **Agregar productos** al menú (en desarrollo)

---

##  Datos de Prueba

### Restaurantes Configurados
1. **Café Barista** - Edificio F
   - Productos: Latte, Cappuccino, Americano, Mocha, etc.
2. **& Café** - Edificio T
   - Productos: Café negro, Iced Latte, Chai Latte, postres
3. **Gitane** - Edificio S
   - Productos: Smoothies, Salchipapa, Tostadas francesas
4. **Go Green** - Edificio D
   - Productos: Wraps veggie, Smoothies verdes, Ensaladas
5. **Panitos y algo más** - Edificio O
   - Productos: Sandwiches, Croissants, Bebidas calientes
6. **Mixtas Frankfurt** - Cafetería Central
   - Productos: Mixtas tradicionales, Hot dogs, Hamburguesas
7. **Golden Harvest** - Edificio P
   - Productos: Hamburguesas, Jamaica, Sushi, Club Sandwich

Todos los productos incluyen:
- Imágenes reales
- Descripciones
- Precios en Quetzales
- Opciones de personalización

---


## 🔧 Instalación y Ejecución

### Prerrequisitos
- Android Studio Ladybug o superior
- JDK 17
- Android SDK 26+
- Gradle 8.13

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/itsadrimartinezzz/BreakBiteUVG
cd BreakBiteUVG
```

2. **Abrir el proyecto en Android Studio**
   - File → Open → Seleccionar la carpeta del proyecto

3. **Sync del proyecto**
```bash
./gradlew build --refresh-dependencies
```

4. **Ejecutar la app**
   - Conectar un dispositivo Android o iniciar un emulador
   - Click en Run (▶️) o usar `Shift + F10`

### Configuración de Red (Opcional)
Si necesitas cargar imágenes desde URLs externas, asegúrate de tener permisos de internet en `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

---

##  Arquitectura Técnica

### Patrón MVVM
- **Model**: Data classes (`ServiceUi`, `OrderUi`, `ProductDetailUi`, etc.)
- **View**: Composables en `ui/` (screens y components)
- **ViewModel**: Lógica en `FakeApi` y estados con `StateFlow`

### Navegación Type-Safe
```kotlin
@Serializable
object LoginDestination

@Serializable
data class ServiceDestination(val serviceId: String)

@Serializable
data class ProductDestination(val productJson: String, val serviceId: String)
```

### Estado Reactivo
```kotlin
// FakeApi.kt
private val barista = MutableStateFlow<List<ServiceOrderUi>>(emptyList())

fun getPedidos(restaurante: String): StateFlow<List<ServiceOrderUi>> {
    return when (restaurante.lowercase()) {
        "barista" -> barista
        // ...
    }
}
```

### Composición de UI
```kotlin
CompositionLocalProvider(LocalAllItems provides allItems) {
    // Pantallas tienen acceso a la lista de items
}

---

##  Testing

### Usuarios de Prueba Recomendados

**Para probar flujo completo:**
1. Login como estudiante: `test@uvg.edu.gt`
2. Hacer un pedido en "Barista"
3. Logout
4. Login como servicio: `barista` / `barista123`
5. Ver el pedido recibido
6. Marcar como completado
7. Logout
8. Login de nuevo como estudiante
9. Verificar notificación de pedido listo

