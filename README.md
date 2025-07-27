# Angels Insurance Inc - Insurance Management System

## Overview

Angels Insurance Inc is a comprehensive insurance management system built with Spring Boot that provides a complete solution for managing insurance policies, claims, customers, and administrative operations. The system features secure authentication, role-based access control, automated notifications, and document generation capabilities.

## Table of Contents
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Security](#security)
- [Scheduled Tasks](#scheduled-tasks)

- ![inc drawio (4)](https://github.com/user-attachments/assets/9d403a89-cc13-43cc-8018-4b4d3dfcda94)


## Features

### Core Features
- **Customer Management**: Complete customer lifecycle management with profile creation and updates
- **Policy Management**: Create, update, and manage various types of insurance policies
- **Claims Processing**: Submit and track insurance claims with supporting documentation
- **Accident Reporting**: Record and manage accident details linked to claims
- **Cancellation Requests**: Handle policy cancellation requests with approval workflow
- **Audit Logging**: Track all system activities for compliance and security

### Administrative Features
- **User Management**: Admin capabilities for managing system users
- **Department Management**: Organize users and operations by departments
- **Notification System**: Automated email notifications for policy renewals and updates
- **Document Generation**: Generate PDF documents for policies and claims

### Security Features
- **JWT Authentication**: Secure token-based authentication system
- **Role-Based Access Control**: Different access levels for ADMIN, USER, and CUSTOMER roles
- **Spring Security Integration**: Enterprise-grade security implementation

## Technology Stack

### Backend
- **Java 21**: Latest LTS version of Java
- **Spring Boot 3.4.3**: Modern microservices framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Database ORM layer
- **PostgreSQL**: Relational database management system

### Libraries & Tools
- **JWT (JSON Web Tokens)**: For secure authentication
- **Lombok**: Reduce boilerplate code
- **MapStruct**: Object mapping framework
- **SpringDoc OpenAPI**: API documentation (Swagger UI)
- **iText**: PDF generation
- **Spring Mail**: Email notifications

## Architecture

The application follows a layered architecture pattern:

```
src/main/java/com/asal/insurance_system/
├── Auth/                  # Authentication related classes
├── Components/            # Scheduled tasks and services
├── Configuration/         # Application configuration
├── Controller/            # REST API endpoints
│   └── Admin/            # Admin-specific controllers
├── DTO/                   # Data Transfer Objects
│   ├── Request/          # Request DTOs
│   └── Response/         # Response DTOs
├── Enum/                  # Enumeration types
├── Exception/             # Custom exceptions and handlers
├── Mapper/                # Object mappers (MapStruct)
├── Model/                 # JPA entities
├── Repository/            # Data access layer
└── Service/              # Business logic layer
```

## Getting Started

### Prerequisites
- Java 21 or higher
- PostgreSQL database
- Maven 3.6+

### Database Setup
1. Create a PostgreSQL database named `Angels-Insurance-Inc`
2. Update the database credentials in `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/Angels-Insurance-Inc
    username: your_username
    password: your_password
```

### Installation
1. Clone the repository:
```bash
git clone https://github.com/shareefmwafy/Angels-Insurance-Inc
cd insurance-system
```

2. Build the project:
```bash
./mvnw clean install
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

The application will start on port 8088 (configurable in application.yml).

### Accessing the Application
- API Base URL: `http://localhost:8088/api/v1`
- Swagger UI: `http://localhost:8088/swagger-ui.html`

## API Documentation

### Authentication
- `POST /api/v1/auth` - Login endpoint

### Customer Management
- `GET /api/v1/customer` - Get all customers (Admin/User)
- `GET /api/v1/customer/{id}` - Get customer by ID
- `POST /api/v1/customer` - Create new customer
- `PUT /api/v1/customer/{id}` - Update customer
- `DELETE /api/v1/customer/{id}` - Delete customer (Admin only)

### Policy Management
- `GET /api/v1/policy` - Get all policies
- `GET /api/v1/policy/{id}` - Get policy by ID
- `GET /api/v1/policy/byCustomer/{customerId}` - Get policies by customer
- `POST /api/v1/policy` - Create new policy
- `PUT /api/v1/policy/{id}` - Update policy
- `DELETE /api/v1/policy/{id}` - Delete policy
- `GET /api/v1/policy/{id}/download` - Download policy document

### Claims Management
- `GET /api/v1/claims` - Get all claims
- `GET /api/v1/claims/{id}` - Get claim by ID
- `POST /api/v1/claims` - Submit new claim
- `PUT /api/v1/claims/{id}` - Update claim
- `DELETE /api/v1/claims/{id}` - Delete claim

### Accident Reporting
- `GET /api/v1/accident` - Get all accidents
- `GET /api/v1/accident/{id}` - Get accident by ID
- `POST /api/v1/accident` - Report new accident
- `PUT /api/v1/accident/{id}` - Update accident details
- `DELETE /api/v1/accident/{id}` - Delete accident record

### Administrative Endpoints
- `GET /api/v1/admin/users` - Get all users
- `POST /api/v1/admin/users` - Create new user
- `GET /api/v1/audit-log` - View audit logs
- `GET /api/v1/notifications` - Get notifications

## Database Schema

### Core Entities

#### Customer
- Extends BasePerson with insurance-specific fields
- Contains policy type preference and claim history
- Implements Spring Security UserDetails for authentication

#### Policy
- Links to Customer entity
- Tracks policy type, status, amount, and validity period
- Supports multiple policy types (defined in EnumPolicyType)

#### Claim
- Associated with Customer and Accident entities
- Tracks claim status, requested/approved amounts
- Supports document attachments

#### Accident
- Records accident details linked to customers
- Used as basis for insurance claims

### Enumerations
- **EnumPolicyType**: Types of insurance policies offered
- **EnumPolicyStatus**: Policy lifecycle states
- **EnumClaimStatus**: Claim processing states
- **Role**: User roles (ADMIN, USER, CUSTOMER)
- **Permission**: Granular permissions for role-based access

## Security

### JWT Configuration
- Token expiration: 24 hours (configurable)
- Secret key stored in application configuration
- Token-based stateless authentication

### Role-Based Access Control
- **ADMIN**: Full system access
- **USER**: Employee-level access
- **CUSTOMER**: Customer-specific operations only

### Security Best Practices
- Password encryption using BCrypt
- CORS configuration for frontend integration
- Method-level security with @PreAuthorize annotations

## Scheduled Tasks

### Automated Processes
1. **Daily Notification Service** (9:00 AM)
   - Sends policy renewal reminders
   - Notifies about expiring policies

2. **Policy Status Update** (Midnight)
   - Updates policy statuses based on expiration dates
   - Marks expired policies automatically

## Configuration

Key configuration parameters in `application.yml`:
- Database connection settings
- JWT secret and expiration
- Email server configuration
- Scheduled task timing
- Logging levels

## Development

### Running Tests
```bash
./mvnw test
```

### Building for Production
```bash
./mvnw clean package
java -jar target/insurance-system-0.0.1-SNAPSHOT.jar
```

### API Testing
Use the built-in Swagger UI at `/swagger-ui.html` for testing API endpoints with proper authentication tokens.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request
---
