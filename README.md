# 🏦 SmartBank Credit Simulation Application
![img.png](docs/img.png)
[![Java](https://img.shields.io/badge/Java-EE-red.svg)](https://www.oracle.com/java/technologies/java-ee-glance.html)
[![Maven](https://img.shields.io/badge/Maven-3.8.1-blue.svg)](https://maven.apache.org/)
[![Hibernate](https://img.shields.io/badge/Hibernate-5.6-yellow.svg)](https://hibernate.org/)

> A robust Java EE application for credit simulation and management, designed for SmartBank's client-facing services.

---

## 📑 Table of Contents
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Getting Started](#-getting-started)
- [Architecture](#-architecture)
- [Testing](#-testing)
- [Project Timeline](#-project-timeline)
- [Evaluation Criteria](#-evaluation-criteria)

---

## ✨ Features

### Core Functionality
🔹 Interactive credit simulation  
🔹 Real-time payment calculations  
🔹 Credit request management  
🔹 Status tracking & history  
🔹 Advanced filtering system

### Client Features
```
📊 Credit Simulation
   ├── Monthly payment calculator
   ├── Real-time updates
   └── Validation system

📝 Credit Requests
   ├── Request submission
   ├── Status tracking
   └── History viewing
```

---

## 🛠 Technology Stack

### Backend
- **Java EE** - Enterprise framework
- **Hibernate/JPA** - ORM & persistence
- **Servlets** - Web components
- **Maven** - Dependency management

### Frontend
- **JSP/JSTL** - View technology
- **HTML5/CSS3** - Markup & styling
- **JavaScript** - Client-side logic

### Testing
- **JUnit** - Unit testing
- **Mockito** - Mocking framework

---

## 🚀 Getting Started

### Prerequisites
```bash
- Java 8 or higher
- Maven 3.6+
- Tomcat/Jetty/GlassFish
- MySQL/PostgreSQL
```

### Installation
1️⃣ Clone the repository
```bash
git clone https://github.com/your-repo/credit-simulation.git
```

2️⃣ Configure database
```xml
<!-- Update persistence.xml -->
<property name="hibernate.connection.url" value="your-db-url"/>
```

3️⃣ Build the project
```bash
mvn clean install
```

4️⃣ Deploy to server
```bash
cp target/credit-simulation.war {server-path}/webapps/
```

---

## 🏗 Architecture

### Project Structure
```
📦 credit-simulation
 ┣ 📂 src/main/java
 ┃ ┣ 📂 config
 ┃ ┣ 📂 entity
 ┃ ┣ 📂 services
 ┃ ┣ 📂 repositories
 ┃ ┗ 📂 servlet
 ┣ 📂 src/main/resources
 ┗ 📂 src/test/java
```

### Design Patterns
- 🎯 MVC Architecture
- 🔄 Singleton Pattern (EntityManagerFactory)
- 💉 Constructor-based Dependency Injection

---

## 🧪 Testing

### Test Coverage
```
📊 Unit Tests
   ├── Business Logic
   ├── Calculations
   └── Data Validation

🔍 Integration Tests
   ├── Database Operations
   └── API Endpoints
```


---

*Made with ❤️ by ouchin55edcx*