# 🚀 Spring Boot Microservices with Eureka, API Gateway, and MySQL

This project demonstrates a **microservices architecture** using:
- **Spring Boot** (Microservices)
- **Spring Cloud Eureka** (Service Discovery)
- **Spring Cloud Gateway** (API Gateway)
- **MySQL** (Database)
- **Docker & Docker Compose** (Containerization)

## 📌 Features
✅ **Service Discovery** using Eureka  
✅ **API Gateway** for routing & load balancing  
✅ **Product & Order Microservices** with MySQL database  
✅ **Dockerized Deployment** with `docker-compose.yml`  

---

## 🏗️ **Architecture Overview**
```
microservices-project/
│── eureka-server/        # Eureka Service Registry
│── api-gateway/          # Spring Cloud Gateway
│── product-service/      # Product Microservice (MySQL)
│── order-service/        # Order Microservice (MySQL)
│── mysql-init-scripts/   # Database initialization script
│── docker-compose.yml    # Docker setup for all services
└── README.md             # Project documentation
```

Each microservice registers itself with **Eureka Server**, and requests go through the **API Gateway**.

---

## 🛠️ **Prerequisites**
Before running the project, ensure you have:
- **JDK 17+**
- **Maven**
- **Docker & Docker Compose**

---

## 🚀 **How to Run the Project**
### 1️⃣ **Clone the Repository**
```bash
git clone https://github.com/arun071/spring-microservices.git
cd spring-microservices
```

### 2️⃣ **Build the Microservices**
```bash
cd eureka-server && mvn clean package -DskipTests
cd ../api-gateway && mvn clean package -DskipTests
cd ../product-service && mvn clean package -DskipTests
cd ../order-service && mvn clean package -DskipTests
cd ..
```

### 3️⃣ **Start All Services Using Docker**
```bash
docker-compose up --build
```

### 4️⃣ **Verify Running Services**
- **Eureka Dashboard** → [http://localhost:8761](http://localhost:8761)
- **API Gateway** → [http://localhost:8080](http://localhost:8080)
- **Product Service** → [http://localhost:8081/products](http://localhost:8081/products)
- **Order Service** → [http://localhost:8082/orders](http://localhost:8082/orders)
- **MySQL** → Connect via `127.0.0.1:3307` (User: `root`, Password: `1234`)

### 5️⃣ **Stop the Services**
```bash
docker-compose down
```

---

## 📦 **Docker Setup (`docker-compose.yml`)**
```yaml
version: '3.8'
services:
  eureka-server:
    build: ./eureka-server
    ports:
      - "8761:8761"
    networks:
      - microservices-net

  api-gateway:
    build: ./api-gateway
    ports:
      - "8080:8080"
    depends_on:
      - eureka-server
    networks:
      - microservices-net

  product-service:
    build: ./product-service
    ports:
      - "8081:8081"
    depends_on:
      - eureka-server
      - mysql
    networks:
      - microservices-net

  order-service:
    build: ./order-service
    ports:
      - "8082:8082"
    depends_on:
      - eureka-server
      - mysql
    networks:
      - microservices-net

  mysql:
    image: mysql:8.0
    container_name: mysql
    restart: always
    ports:
      - "3307:3306"
    environment:
      - MYSQL_ROOT_PASSWORD=1234
    networks:
      - microservices-net
    volumes:
      - ./mysql-init-scripts:/docker-entrypoint-initdb.d

networks:
  microservices-net:
    driver: bridge
```

---

## 🔗 **API Endpoints**
| Service          | Endpoint                        | Description               |
|-----------------|--------------------------------|---------------------------|
| **Product**     | `GET http://localhost:8080/products`                | Get all products          |
| **Product**     | `POST http://localhost:8080/products`               | Add a new product         |
| **Order**       | `GET http://localhost:8080/orders`                  | Get all orders            |
| **Order**       | `POST http://localhost:8080/orders`                 | Create an order           |



## 📬 **Need Help?**
If you have any issues, feel free to open an issue on GitHub! 😊

---

🚀 **Now you are ready to build & deploy microservices!** 🎯

