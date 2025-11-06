# Tienda Online - Backend (Versión 1)

Proyecto backend para una tienda online construido con **Spring Boot**, **Java 25** y **MySQL**. Este repositorio corresponde a la **Versión 1**, centrada exclusivamente en el **CRUD de productos**, sentando las bases para la evolución futura del sistema.

---

## ✅ Objetivo de la Versión 1

La primera versión del proyecto implementa:

* CRUD completo de productos.
* Validaciones en DTOs y en la capa de servicio.
* Paginación incluida.
* Filtros básicos para listar productos.
* Respuesta estandarizada tanto para **item individual** como para **lista**.
* Documentación generada mediante **OpenAPI / Swagger**.
* Integración con **MySQL** mediante Docker (solo para la base de datos por ahora).

Esta versión actúa como la base del sistema de e-commerce que evolucionará con el tiempo.

---

## ✅ Tecnologías utilizadas

**Backend:**

* Java 25
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Validation
* OpenAPI (Swagger UI)

**Base de datos:**

* MySQL (contenedor Docker)

**Futuro:**

* Frontend en React (no incluido aún)
* Docker Compose para todo el ecosistema

---

## ✅ Ejecución del proyecto

Puedes ejecutar el proyecto de dos maneras:

### 1. Usando Maven Wrapper

```
./mvnw spring-boot:run
```

### 2. Usando Maven instalado en tu sistema

```
mvn spring-boot:run
```

### Base de datos (MySQL con Docker)

Asegúrate de tener MySQL corriendo en un contenedor:

```
docker compose up -d
```

---

## ✅ Documentación de la API

Una vez ejecutado el proyecto, la documentación estará disponible en:

* **Swagger UI:** `http://localhost:8080/swagger-ui.html`
* **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

Incluye:

* Endpoints del CRUD de productos
* Validaciones
* Ejemplos
* Modelos

---

## ✅ Arquitectura (Versión 1)

```
src/main/java
└── com.tienda
     ├── common
     │     ├── exceptions
     │     └── response
     └── modules
           ├── products
           │     ├── controller
           │     ├── service
           │     ├── repository
           │     ├── dto
           │     └── model
           └── user   (para la Versión 2)

```

* **controller:** Manejo de endpoints.
* **service:** Lógica de negocio y validaciones extra.
* **repository:** Acceso a datos con JPA.
* **dto:** Entrada y salida de datos con validaciones.
* **common:** Manejo de excepciones y formato estándar de respuesta y mas modulos importantes.

---

## ✅ Roadmap del proyecto

Este proyecto crecerá mediante versiones progresivas, ampliando funcionalidades y robustez.

### **v1 – CRUD de Productos** ✅ *Versión actual*

### **v2 – CRUD de Usuarios**

### **v3 – Autenticación y Autorización** (JWT, roles, permisos)

### **v4 – Imágenes y archivos** (upload, almacenamiento, asociación a productos)

### **v5 – Toda la lógica de negocios** (categorías, carritos, órdenes, facturas, pago, stock, etc.)

Cada versión se construirá sobre la anterior, manteniendo una arquitectura modular y escalable.

---

## ✅ Estado actual del proyecto

✔️ En desarrollo de la **Versión 1**

El objetivo es tener una base sólida, organizada y preparada para las siguientes etapas del proyecto.

---

## ✅ Notas finales

Este proyecto busca evolucionar hacia un backend completo de e-commerce, manteniendo orden, buenas prácticas y una arquitectura clara. Toda contribución o iteración futura seguirá la estructura aquí definida.
