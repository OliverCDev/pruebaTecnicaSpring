# Prueba Técnica - Spring Boot

## Descripción

Este proyecto es una API desarrollada con Spring Boot que proporciona funcionalidades para la consulta y gestión de órdenes, productos y usuarios. La arquitectura sigue el patrón MVC y cuenta con capas bien definidas para repositorios, servicios y controladores REST.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.4.3**
- **Maven**
- **Lombok**
- **Spring Security**
- **Spring Boot DevTools**
- **Tomcat**

## Estructura del Proyecto

```
pruebaTecnica/
│── src/
│   ├── main/java/com/olivercdev/pruebaTecnica/
│   │   ├── controllers/             # Controladores para los endpoints
│   │   ├── models/                  # Modelos de entidades
│   │   |   ├── repository/
│   │   |   |    |── Ordenes/
│   │   |   |    |── Users/
│   │   |   |── request/
│   │   |   |── response/
│   │   ├── repository/              # Capa de acceso a datos
│   │   │   ├── ConsultaOrdenes/
│   │   │   ├── ConsultaProductos/
│   │   │   ├── ConsultaUsuarios/
│   │   ├── rest/client/             # Cliente para consumir API externa
│   │   ├── services/                # Lógica de negocio
│   │   │   ├── Ordenes/
│   │   │   ├── PagoOrdenes/
│   │   │   ├── Productos/
│   │   ├── resources/
│   │   │   ├── static/
│   │   │   ├── templates/
│   │   │   ├── application.properties # Configuración de la aplicación
│── test/                             # Pruebas unitarias
│── pom.xml                           # Configuración de dependencias
```

## Instalación y configuración

1. Clonar el repositorio:
   ```sh
   git clone https://github.com/OliverCDev/pruebaTecnicaSpring.git
   ```
2. Acceder al directorio del proyecto:
   ```sh
   cd pruebaTecnica
   ```
3. Compilar el proyecto con Maven:
   ```sh
   mvn clean install
   ```
4. Ejecutar la aplicación:
   ```sh
   mvn spring-boot:run
   ```
## Endpoints principales

| Método  | URL            | Descripción                |
| ------  | ------------   | -------------------------- |
| POST    | `/ordenCliente`| Obtener lista de órdenes segun cliente   |
| POST    | `/ordenes`     | Obtener lista de órdenes     |
| POST    | `/productos`   | Obtener lista de productos |
| POST    | `/pago`        | Simulacion de pago         |

## Autor

OliverCDev
