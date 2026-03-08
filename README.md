# Yumzi Backend

Yumzi is a robust and scalable backend application designed for a food delivery platform, similar to popular services like Yemeksepeti, Trendyol Go, and UberEats. It provides a comprehensive set of RESTful APIs to handle everything from user authentication and restaurant discovery to menu exploration, shopping cart management, and order placement.

👉 **Mobile Application:** You can find the source code for the mobile client that consumes these backend APIs [right here](https://github.com/ismaildrcn/yumzi).

## 🚀 Features

*   **Authentication & Authorization:** Secure, stateless authentication using Spring Security and JSON Web Tokens (JWT).
*   **Restaurant & Menu Management:** Detailed APIs for browsing restaurants, categories, cuisines, and specific menu items.
*   **Shopping Cart System:** Robust cart management for adding/removing items before placing an order.
*   **Search & Discovery:** Advanced search capabilities for restaurants and food items, including tracking recent searches for a better user experience.
*   **User Profiles & Favorites:** Management of user delivery addresses and favorite restaurants/items.
*   **API Documentation:** Interactive API documentation via Swagger UI / OpenAPI.

## 🏗️ Architecture & Technologies

The project is built using a modern Java stack, following a monolithic REST API architecture with a clear separation of concerns (Controllers, Services, Repositories).

*   **Language:** Java 25
*   **Framework:** Spring Boot (v4.0.1)
*   **Security:** Spring Security & jjwt (JSON Web Token)
*   **Data Access:** Spring Data JPA, Hibernate
*   **Advanced Mappings:** Hypersistence Utils (for JSON columns and advanced data types)
*   **Build Tool:** Maven
*   **Documentation:** Springdoc OpenAPI (Swagger)
*   **Utilities:** Lombok (to reduce boilerplate), Spring Boot Validation

## 🗄️ Database Choices & Rationale

Yumzi utilizes a dual-database approach to balance persistence, relational integrity, and high performance:

### 1. PostgreSQL (Primary Relational Database)
*   **Why PostgreSQL?** It is an advanced, enterprise-grade open-source relational database. For a food delivery app, maintaining ACID compliance and strict relationships between Users, Orders, Restaurants, and Payments is critical.
*   **Use Cases:** Storing persistent data such as user credentials, addresses, restaurant profiles, complex menu hierarchies, and order histories.

### 2. Redis (In-Memory Data Store & Cache)
*   **Why Redis?** Redis provides sub-millisecond response times, making it ideal for managing temporary data that requires high-speed read and write access.
*   **Use Cases:**
    *   **Shopping Cart Management:** User carts are stored directly in Redis to facilitate lightning-fast read/write operations as users add or remove items. A Time-To-Live (TTL) is assigned to the cart objects, meaning that if a user abandons their cart, it is automatically expired and removed from memory. This approach significantly reduces the load on the primary relational database.

## 📁 Project Structure

```text
src/main/java/com/ismaildrcn/
├── config/         # Application, Redis, Security, and Swagger configurations
├── controller/     # REST API endpoints (Cart, Auth, Restaurants, Users, etc.)
├── exception/      # Custom exceptions and error messages
├── handler/        # Global exception handling (ControllerAdvice)
├── interceptor/    # Request interceptors (e.g., for logging)
├── jwt/            # JWT generation, validation, and filtering
├── model/          # Entities, DTOs, Enums, and Embeddables
├── repository/     # Spring Data JPA and Redis repositories
├── service/        # Business logic implementations
└── utils/          # Helper classes and utilities
```

## 🛠️ Getting Started

### Prerequisites
*   Java 25 (JDK)
*   PostgreSQL running on `localhost:5432` (or update `application.properties`)
*   Redis running on `localhost:6379`
*   Maven

### Running the Application

1. Ensure your PostgreSQL instance has a database created (by default `yumzi`).
2. Update `src/main/resources/application.properties` with your PostgreSQL username and password if necessary.
3. Run the application using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Access the Swagger UI for API testing at: `http://localhost:8080/swagger-ui.html` (port may vary based on your config).
