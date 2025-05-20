# UPI Backend Service

This project is a backend RESTful API for a UPI (Unified Payments Interface) based payment system. It supports user registration, balance inquiry, money transfer, and transaction history retrieval.

---

## Features

- User Registration with secure PIN storage (BCrypt)
- Account balance check
- Add money to user account
- Enable/Disable UPI for a user
- Transfer money between users with validations:
    - Secret PIN validation
    - Insufficient balance check
    - Maximum daily transfer limits (per transaction and daily total)
    - Maximum number of transfers per day
    - Receiver's balance limit check
- Transaction history retrieval

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL (or any relational database)
- BCrypt Password Encoder for PIN hashing
- Jakarta Validation (for request validation)
- SLF4J + Logback for logging

---

## Setup Instructions

1. **Clone the repository**

   ```bash
   git clone https://github.com/yourusername/upi-backend.git
   cd upi-backend
