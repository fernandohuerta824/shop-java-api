# Tienda Online - Backend (Versión 1+)

Proyecto backend para una tienda online construido con **Spring Boot**, **Java 25** y **MySQL**. Esta versión corresponde a la **Versión 1+**, con **CRUD de productos**, **CRUD de categorías**, integración con **Cloudinary** para manejo de imágenes y la relación **categorías N:M** lista para asignar productos a categorías.

---

## ✅ Objetivo de la Versión 1+

Esta versión del proyecto incluye:

* CRUD completo de **productos**.
* CRUD completo de **categorías**.
* Relación **categorías N:M** entre productos y categorías.
* Validaciones en DTOs y en la capa de servicio.
* Paginación incluida.
* Filtros básicos para listar productos y proximamento por categorias.
* Respuesta estandarizada tanto para **item individual** como para **lista**.
* Documentación generada mediante **OpenAPI / Swagger**.
* Integración con **MySQL** mediante Docker.
* Integración con **Cloudinary** para gestión de imágenes.

---

## ✅ Tecnologías utilizadas

**Backend:**

* Java 25
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Validation
* OpenAPI (Swagger UI)
* Cloudinary SDK

**Base de datos:**

* MySQL (contenedor Docker)

---

## ✅ Ejecución del backend

Puedes ejecutar el proyecto de dos maneras:

### 1. Usando Maven Wrapper

```bash
./mvnw spring-boot:run
```

### 2. Usando Maven instalado en tu sistema

```bash
mvn spring-boot:run
```

### Base de datos (MySQL con Docker)

Asegúrate de tener MySQL corriendo en un contenedor:

```bash
docker compose up -d
```

---

## ✅ Integración con el frontend

El frontend en React se conecta al backend mediante los endpoints de la API.
Asegúrate de que:

* El backend esté corriendo en `http://localhost:8080`.
* El frontend tenga configurada la URL base del backend para consumir los endpoints.

Repositorio del frontend:
[**Frontend React - Tienda Online**](https://github.com/fernandohuerta824/shop-react-app)

---

## ✅ Documentación de la API

Una vez ejecutado el backend, la documentación estará disponible en:

* **Swagger UI:** `http://localhost:8080/swagger-ui.html`
* **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

Incluye:

* Endpoints del CRUD de productos y categorías
* Validaciones
* Ejemplos
* Modelos
* Documentación de integración con Cloudinary (subida de imágenes)

---

## ✅ Arquitectura (Versión 1+)

```
ShopApplication.java
│
├───common
│   ├───config
│   └───constants
│      
│
├───domain
│   ├───exception
│   ├───mapper
│   ├───response
│   │       ApiPageResponse.java
│   │       ApiResponse.java
│   │       BuildResponse.java
│   └───validation
│
└───modules
    ├───category
    ├───cloudinary
    ├───handlerException
    └───product
```

**Explicación rápida:**

* `common` → configuración general, constantes y seeds iniciales.
* `domain` → lógica transversal: códigos, excepciones, mappers, respuestas y validaciones.
* `modules` → cada módulo es independiente (productos, categorías, Cloudinary, manejo de excepciones).

---

## ✅ Roadmap del proyecto

### **v1 – CRUD de Productos y Categorías** ✅ *Versión actual*

### **v1.1 – Cloudinary + Asignación Categorías N:M**

### **v2 – CRUD de Usuarios**

### **v3 – Autenticación y Autorización** (JWT, roles, permisos)

### **v4 – Toda la lógica de negocios** (carritos, órdenes, facturas, pago, stock, etc.)

Cada versión se construirá sobre la anterior, manteniendo una arquitectura modular y escalable.

---

## ✅ Estado actual del proyecto

✔️ En desarrollo de la **Versión 1+**

* CRUD de productos y categorías funcionando.
* Cloudinary integrado para subir imágenes.
* Relación **categorías N:M** implementada (asignación pendiente).
* Frontend en React desarrollado en repositorio separado.

