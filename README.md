# Events Management API

A robust RESTful API designed to manage Events and Venues, complying with specific User Stories. This project demonstrates modern Java development practices using **Spring Boot 3**, **Hexagonal Architecture** (Ports and Adapters), **Docker**, and **JWT Security**.

The application decouples domain logic from infrastructure, ensuring maintainability and scalability.

---

## 🚀 Key Features

* **Hexagonal Architecture:** Clean separation of concerns (Domain, Application, Infrastructure).
* **Dockerized Environment:** Easy setup with `docker-compose` for both the Application and MySQL database.
* **Security:** Stateless authentication using **JWT** (JSON Web Tokens) and Spring Security.
* **Database Migrations:** Version control for database schema using **Flyway**.
* **API Documentation:** Interactive documentation via **Swagger UI (OpenAPI)**.
* **Observability:** Metrics exposed via **Spring Boot Actuator** and **Prometheus**.
* **Integration Testing:** robust testing with **Testcontainers**.
* **DTO Mapping:** Efficient object mapping with **MapStruct**.

---

## 🛠 Tech Stack

* **Java:** 21
* **Spring Boot:** 3.3.6
* **Build Tool:** Maven
* **Database:** MySQL 8.0 (Docker/Production), H2 (Test)
* **Containerization:** Docker & Docker Compose
* **Documentation:** Springdoc OpenAPI (Swagger)

---

## 📂 Project Structure

The project follows a strict **Hexagonal Architecture** structure:

```text
src/main/java/com/lovelace/eventsUserStories
├── application
│   └── usecase         # Business logic implementation (User Cases)
├── domain
│   ├── model           # Core business entities (Event, Venue, User)
│   ├── ports           # Input/Output interfaces (In/Out Ports)
│   └── exception       # Domain exceptions
└── infrastructure
    ├── adapters
    │   ├── in          # Driving Adapters (Controllers, Security, DTOs)
    │   └── out         # Driven Adapters (JPA Repositories, Entities)
    └── config          # Framework configuration
````

-----

## 🐳 Getting Started (Docker)

The easiest way to run the application is using Docker Compose.

### Prerequisites

  * Docker
  * Docker Compose

### Installation & Run

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/xXCpFireXx/Events-Cristian-Penagos.git
    cd Events-Cristian-Penagos
    ```

2.  **Start the services:**

    ```bash
    docker-compose up --build
    ```

      * This will build the Spring Boot JAR (UserStory-1).
      * Start the **MySQL** container on port `3307`.
      * Start the **Application** container on port `8080`.

3.  **Access the API:**

      * API Root: `http://localhost:8080`
      * Swagger UI: `http://localhost:8080/swagger-ui/index.html`

-----

## ⚙️ Configuration

### Environment Variables

The `docker-compose.yml` file is pre-configured to override local settings and connect to the containerized database.

| Variable | Description | Default (Docker) |
| :--- | :--- | :--- |
| `SPRING_DATASOURCE_URL` | Database Connection URL | `jdbc:mysql://mysqldb:3306/events_db...` |
| `SPRING_DATASOURCE_USERNAME` | Database User | `user1` |
| `SPRING_DATASOURCE_PASSWORD` | Database Password | `user123` |
| `JWT_SECRET` | Secret key for tokens | *(Set in properties)* |

### Database

  * **Name:** `events_db`
  * **External Port:** `3307` (mapped to container's 3306)
  * **Persistence:** Data is persisted in the `mysql_data` volume.

-----

## 🔌 API Endpoints

Full documentation is available in Swagger, but here are the main resources:

### 🔐 Authentication

  * `POST /auth/register` - Create a new user account.
  * `POST /auth/login` - Authenticate and retrieve a Bearer Token.

> **Note:** Most endpoints require the `Authorization: Bearer <token>` header.

### 🏟 Venues

  * `POST /venues` - Create a Venue.
  * `GET /venues` - List all Venues.
  * `GET /venues/{id}` - Get a Venue by ID.
  * `PUT /venues/{id}` - Update a Venue.
  * `DELETE /venues/{id}` - Delete a Venue.

### 📅 Events

  * `POST /events` - Create an Event (linked to a Venue).
  * `GET /events` - List all Events.
  * `GET /events/{id}` - Get an Event by ID.
  * `PUT /events/{id}` - Update an Event.
  * `DELETE /events/{id}` - Delete an Event.

-----

## 🧪 Testing

To run the integration tests (which use Testcontainers to spin up a temporary MySQL instance):

```bash
./mvnw test
```

-----

## 👥 Contributors

  * **Cristian Penagos** - *Initial Work*
