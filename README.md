# Angels Insurance Inc - Insurance Management System

![inc drawio (4)](https://github.com/user-attachments/assets/9d403a89-cc13-43cc-8018-4b4d3dfcda94)

## Overview

Angels Insurance Inc is a comprehensive insurance management system built with Spring Boot 3.4.3 and Java 21. The system provides a complete solution for managing insurance policies, claims, customers, and administrative operations with robust security, automated notifications, and document generation capabilities.

## Features

### Core Insurance Operations
- **Policy Management**: Create, update, and manage various insurance policies
- **Claims Processing**: Handle insurance claims with status tracking and approval workflows
- **Customer Management**: Comprehensive customer information and profile management
- **Accident Reporting**: Track and manage accident incidents related to insurance claims

### Administrative Features
- **User Management**: Multi-role user system (Admin, User, Customer)
- **Department Management**: Organize users into departments
- **Audit Logging**: Complete audit trail of system activities
- **Policy Cancellation Requests**: Handle policy cancellation workflows

### Automated Services
- **Policy Renewal Reminders**: Automated notifications for expiring policies
- **Policy Status Updates**: Scheduled status updates for policies
- **Email Notifications**: Automated email notifications for various events

### Security & Authentication
- **JWT Authentication**: Secure token-based authentication
- **Role-Based Access Control**: Granular permissions (Admin, User, Customer)
- **Spring Security Integration**: Enterprise-grade security implementation

### Additional Features
- **PDF Document Generation**: Generate policy documents using iText PDF
- **RESTful API**: Comprehensive REST API with OpenAPI documentation
- **Database Integration**: PostgreSQL database with JPA/Hibernate
- **Email Integration**: SMTP email service for notifications

## Technology Stack

### Backend Framework
- **Spring Boot 3.4.3**: Main application framework
- **Spring Security 6.4.3**: Authentication and authorization
- **Spring Data JPA**: Database operations and ORM
- **Spring Web**: REST API development
- **Spring Mail**: Email functionality
- **Spring Scheduling**: Automated tasks and cron jobs

### Database
- **PostgreSQL**: Primary database
- **Hibernate**: ORM and database migrations

### Security & Authentication
- **JWT (JSON Web Tokens)**: Token-based authentication
- **BCrypt**: Password encryption
- **JJWT 0.12.6**: JWT implementation

### Documentation & Testing
- **SpringDoc OpenAPI 2.8.6**: API documentation (Swagger UI)
- **Spring Boot Test**: Testing framework
- **Spring Boot DevTools**: Development utilities

### Additional Libraries
- **Lombok 1.18.32**: Reduce boilerplate code
- **MapStruct 1.6.3**: Object mapping
- **iText7 7.2.5**: PDF document generation
- **Jakarta Validation**: Input validation

## Project Structure

```
src/main/java/com/asal/insurance_system/
├── Auth/                          # Authentication DTOs
├── Components/                    # Scheduled services and components
├── Configuration/                 # Application configuration
├── Controller/                    # REST API endpoints
│   └── Admin/                    # Admin-specific controllers
├── DTO/                          # Data Transfer Objects
├── Enum/                         # Enumerations (roles, statuses, types)
├── Exception/                    # Custom exceptions
├── Mapper/                       # MapStruct mappers
├── Model/                        # JPA entities
├── Repository/                   # Data access layer
└── Service/                      # Business logic layer
```

## Key Entities

### Core Models
- **Customer**: Insurance customers with policies and claims
- **Policy**: Insurance policies with types, statuses, and coverage details
- **Claim**: Insurance claims with approval workflows
- **User**: System users (employees, admins)
- **Accident**: Accident reports linked to claims

### Supporting Models
- **Notification**: System notifications and alerts
- **AuditLog**: Comprehensive audit trail
- **Department**: Organizational structure
- **CancellationRequest**: Policy cancellation workflows

## API Endpoints

### Authentication
- `POST /api/v1/auth` - User authentication and JWT token generation

### Policy Management
- `GET /api/v1/policy` - List all policies
- `POST /api/v1/policy` - Create new policy
- `GET /api/v1/policy/{id}` - Get policy details
- `PUT /api/v1/policy/{id}` - Update policy
- `DELETE /api/v1/policy/{id}` - Delete policy

### Claims Management
- `GET /api/v1/claims` - List all claims
- `POST /api/v1/claims` - Create new claim
- `PUT /api/v1/claims/{id}` - Update claim status

### Customer Management
- `GET /api/v1/customers` - List all customers
- `POST /api/v1/customers` - Create new customer
- `GET /api/v1/customers/{id}` - Get customer details
- `PUT /api/v1/customers/{id}` - Update customer

### Administrative
- `GET /api/v1/audit-logs` - View audit logs
- `GET /api/v1/notifications` - Manage notifications
- `GET /api/v1/admin/**` - Admin-specific operations

## User Roles & Permissions

### ADMIN
- Full system access
- User management capabilities
- Policy and claims administration
- System configuration access

### USER (Employee)
- Policy management
- Claims processing
- Customer management
- Limited administrative functions

### CUSTOMER
- View personal policies
- Submit claims
- Update personal information
- View notifications

## Configuration

### Database Configuration
```yaml
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/Angels-Insurance-Inc
    username: postgres
    password: [configured]
  jpa:
    hibernate:
      ddl-auto: update
    database: postgresql
```

### JWT Configuration
```yaml
jwt:
  secret: [configured]
  token:
    expiration: 86400000 # 1 day
```

### Scheduled Tasks
```yaml
scheduling:
  cron:
    dailyNotification: "0 0 9 * * ?" # Daily at 9:00 AM
    policy-status-update: "0 0 0 * * ?" # Daily at midnight
```

## Installation & Setup

### Prerequisites
- Java 21 or higher
- PostgreSQL 12+
- Maven 3.6+

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone [repository-url]
   cd insurance-system
   ```

2. **Configure Database**
   - Create PostgreSQL database: `Angels-Insurance-Inc`
   - Update database credentials in `application.yml`

3. **Build the application**
   ```bash
   ./mvnw clean install
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access the application**
   - Application: http://localhost:8088
   - API Documentation: http://localhost:8088/swagger-ui.html

## Development

### Running in Development Mode
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Building for Production
```bash
./mvnw clean package -Dmaven.test.skip=true
```

### Running Tests
```bash
./mvnw test
```

## API Documentation

The application includes comprehensive API documentation using SpringDoc OpenAPI. Access the interactive documentation at:

- **Swagger UI**: http://localhost:8088/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8088/v3/api-docs

## Features in Detail

### Automated Policy Renewal Reminders
The system automatically checks for policies expiring within 7 days and sends email notifications to customers.

### Claims Processing Workflow
1. Customer submits claim with supporting documents
2. System validates claim against policy coverage
3. Claims processor reviews and approves/denies
4. Automated notifications sent to customer
5. Audit log maintains complete history

### Document Generation
The system generates PDF documents for:
- Policy certificates
- Claims reports
- Customer statements

### Security Implementation
- JWT-based stateless authentication
- Role-based access control with granular permissions
- Password encryption using BCrypt
- CORS configuration for frontend integration

## Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/new-feature`)
3. Commit changes (`git commit -am 'Add new feature'`)
4. Push to branch (`git push origin feature/new-feature`)
5. Create Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and questions, please contact the development team or create an issue in the repository.

---

**Angels Insurance Inc** - Comprehensive Insurance Management Solution
