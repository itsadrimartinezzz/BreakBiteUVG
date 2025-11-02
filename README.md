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

##  Servicios Implementados

###  Login
- **Tipo:** Servicio simulado  
- **Descripción:** Emula un sistema de autenticación que valida credenciales y mantiene la sesión activa mediante **DataStore**.  
  En futuras versiones se integrará una **API real** para autenticación remota.

###  Gestión de pedidos
- **Tipo:** Simulación  
- **Descripción:** Permite crear y actualizar pedidos, manejando estados como  
  `"En preparación"`, `"Listo"` y `"Completado"`.  
  Actualmente opera localmente para pruebas sin conexión.

###  Servicios de restaurante (`FakeApi.kt`)
- **Tipo:** API Fake Local  
- **Descripción:** Devuelve información sobre los restaurantes disponibles  
  (**Barista, Café Gitane, Saul, Go Green**, etc.) y sus productos (**bebidas, comidas, postres**).  
  Sirve como reemplazo temporal de una API real.

###  Llamada a Internet (Retrofit Example)
- **Tipo:** Ejemplo funcional  
- **Descripción:** Se usa **Retrofit** para conectarse a un endpoint público de prueba,  
  cumpliendo con el requisito de realizar al menos una llamada real a internet sin afectar la app.

---

##  Funcionalidades

- Registro e inicio de sesión de usuarios.  
- Visualización de menús de restaurantes.  
- Creación y seguimiento de pedidos.  
- Listado de pedidos activos y completados.  
- Navegación entre pantallas de usuario y servicio.

---

##  Estructura del Proyecto


---

## Librerías Utilizadas

- **Room** → persistencia local (usuarios y pedidos).  
- **Retrofit** → llamadas HTTP.  
- **Kotlinx Serialization** → serialización/deserialización de datos.  
- **Navigation Compose** → navegación entre pantallas.  
- **DataStore Preferences** → almacenamiento de sesión.  
- **Coroutines & Flow** → operaciones asíncronas.  
- **Material3 (Compose)** → interfaz con estilo Material Design.

---

##  Tecnologías

| Categoría | Herramienta |
|------------|--------------|
| **Lenguaje** | Kotlin |
| **Framework** | Jetpack Compose |
| **Arquitectura** | MVVM |
| **Persistencia** | Room & DataStore |
| **Diseño** | Material Design |



