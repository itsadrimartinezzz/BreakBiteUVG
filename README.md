#  BreakBite UVG 

##  Descripción  
**BreakBite UVG** es una aplicación móvil desarrollada en **Kotlin** con **Jetpack Compose**.  
Su objetivo es **optimizar la experiencia de compra de alimentos en la cafetería de la Universidad del Valle de Guatemala (UVG)**.  

La app permite a los usuarios:
- Iniciar sesión.
- Explorar los menús de los diferentes restaurantes.
- Realizar pedidos.
- Recogerlos cuando estén listos.

El proyecto sigue el patrón **MVVM (Model-View-ViewModel)**, utiliza **Room** para la persistencia local y **Navigation Compose** para la navegación entre pantallas.  
Incluye servicios simulados que emulan la comunicación con un servidor remoto mediante una **API fake local**.

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

###  Login Multi-Rol
- **Tipo:** Servicio simulado  
- **Descripción:** Sistema de autenticación dual que soporta:
  - **Estudiantes**: Login con correo UVG (@uvg.edu.gt)
  - **Servicios**: Login con nombre del restaurante
- Cada servicio tiene acceso a su panel personalizado con:
  - Logo y branding específico
  - Menú propio de productos


###  Gestión de Pedidos por Servicio
- **Tipo:** Simulación multi-servicio  
- **Descripción:** Sistema independiente de pedidos donde cada restaurante maneja:
  - Estados: `"Pendiente"`, `"En preparación"`, `"Completado"`
  - Lista de productos con imágenes
- Permite completar pedidos y actualizar estados en tiempo real

###  API de Restaurantes (`FakeApi.kt`)
- **Tipo:** API Fake Local  
- **Descripción:** Devuelve información dinámica sobre:
  - 7 restaurantes disponibles con logos reales
  - Productos categorizados (bebidas, comidas, postres)
  - Información específica por servicio (nombre, ubicación, tiempo de espera)
- Sirve como reemplazo temporal de una API real

---

##  Funcionalidades

###  Vista Estudiante
-  Explorar servicios disponibles
-  Ver menús con imágenes de productos
-  Personalizar pedidos (tamaños, extras)
-  Historial de pedidos
-  Notificaciones de estado

###  Vista Servicio
-  Panel personalizado con logo y branding
-  Gestión de pedidos en tiempo real
-  Visualización de productos con imágenes
-  Cambio de estados de pedidos
-  Vista de tienda para gestionar menú
-  Cerrar sesión seguro

---

##  Estructura del Proyecto

```
app/
├── data/
│   ├── FakeApi.kt              # API simulada con datos de servicios
│   ├── ServiceUi.kt            # Modelo de servicio
│   ├── OrderUi.kt              # Modelo de pedido (estudiante)
│   └── ProductUi.kt            # Modelo de producto
├── ui/
│   ├── screens/
│   │   ├── LoginScreen.kt      # Pantalla de login dual
│   │   └── SignUpScreen.kt     # Registro
│   ├── userScreens/            # Pantallas de estudiante
│   │   ├── UserHomeScreen.kt
│   │   ├── UserServiceScreen.kt
│   │   └── UserOrderHistoryScreen.kt
│   ├── serviceScreens/         # Pantallas de servicio
│   │   ├── ServiceModels.kt    # Modelos compartidos
│   │   ├── ServiceOrdersScreen.kt
│   │   ├── ServiceHomeScreen.kt
│   │   └── ServiceOrderDetailScreen.kt
│   └── navegation/
│       └── navegation.kt       # Sistema de navegación multi-rol
└── MainActivity.kt
```

---

##  Librerías Utilizadas

###  UI & Navegación
```gradle
// Jetpack Compose - UI declarativa
implementation("androidx.compose.material3:material3:1.1.2")
implementation("androidx.compose.material:material-icons-extended:1.5.4")

// Navigation Compose - Navegación type-safe
implementation("androidx.navigation:navigation-compose:2.7.5")
```

###  Carga de Imágenes
```gradle
// Coil - Carga de imágenes desde URLs
implementation("io.coil-kt:coil-compose:2.5.0")
```

###  Serialización
```gradle
// Kotlinx Serialization - Pasar objetos entre pantallas
implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
```

###  Arquitectura
```gradle
// Lifecycle & ViewModel - Gestión de estado
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

// Core Android KTX
implementation("androidx.core:core-ktx:1.12.0")
```

---

##  Tecnologías

| Categoría | Herramienta | Uso en el Proyecto |
|------------|--------------|-------------------|
| **Lenguaje** | Kotlin | Lenguaje principal |
| **Framework** | Jetpack Compose | UI declarativa moderna |
| **Arquitectura** | MVVM | Separación de capas |
| **Navegación** | Navigation Compose | Rutas type-safe |
| **Imágenes** | Coil | Carga asíncrona desde URLs |
| **Serialización** | Kotlinx Serialization | Paso de datos entre pantallas |
| **Diseño** | Material Design 3 | Interfaz consistente |

---

##  Características Destacadas

###  Sistema Multi-Rol
- Login dual: estudiantes (email UVG) y servicios (nombre del restaurante)
- Navegación independiente según el rol


---

## 🔧 Instalación

1. Clona el repositorio
```bash
git clone https://github.com/itsadrimartinezzz/BreakBiteUVG
```

2. Abre el proyecto en Android Studio

3. Sync del proyecto
```bash
./gradlew build --refresh-dependencies
```

4. Ejecuta la app en un emulador o dispositivo físico

---
