# UPI Application

This repository contains both the **Frontend** and **Backend** source code for a simple UPI app with features:
- Enable/Disable UPI for a phone number
- Transfer money to another UPI number
- Check account balance
- Add money to the UPI number

---

## Frontend

### Tech Stack
- ReactJS (with Hooks)
- JavaScript (ES6+)
- Axios (for API calls)
- CSS (or any preferred styling library)
- npm (Node Package Manager)

### Features
- User registration and login with secret PIN
- Enable/Disable UPI functionality
- Money transfer with validations (max daily transfers, max amount per transaction and day)
- Balance checking and money addition with constraints
- Transaction history view

### Setup Instructions

1. Clone the repo:
   ```bash
   git clone <repo-url>
   cd UPI_App_Frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm start
   ```

4. The app will run at [http://localhost:3000](http://localhost:3000) by default.

### Configuration

- Update the backend API base URL in `src/services/api.js` if needed.

### Folder Structure

```
UPI_App_Frontend/
├── public/
├── src/
│   ├── components/
│   ├── services/
│   └── ...
├── package.json
└── README.md
```

---

## Backend

### Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL (or any relational DB)
- Maven

### Features
- User registration with secret PIN
- Enable/Disable UPI functionality
- Money transfer with validations (max daily transfers, max amount per transaction and day)
- Balance checking and money addition with constraints
- Transaction history retrieval

### Setup Instructions

1. Clone the repo:
   ```bash
   git clone <repo-url>
   cd upi-backend
   ```

2. Configure your database settings in `src/main/resources/application.properties`.

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

5. The backend will run at [http://localhost:8080](http://localhost:8080) by default.

### Folder Structure

```
upi-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── pom.xml
└── README.md
```

---

## License

This project is for educational/demo purposes.
