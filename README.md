# 🏨 Hotel Reservation System
A Full-Stack Hotel Management System built with Spring Boot Microservices and a React frontend.

## Introduction
-  This project is a development of a small set of **Spring Boot** and **Cloud** based Microservices projects that implement Microservices architecture and design patterns, and coding best practices.
  
-  This project uses cutting edge technologies : 
     -  Development Java 17, Springboot 3.4 and gradle for build
     -  Spring cloud gateway as an API Gateway
     -  Eureka as a discovery service
     -  Spring config server and profile for config management
     -  Micrometer and zipkin for distributed tracing
     -  Springboot admin for monitoring
     -  Rest for synchron communication between microservices
     -  Database migration with flyway and postgres
     -  Reactive programming with webflux spring data reactive and mongodb
     -  Deployment docker and docker-compose
---

## Getting started
### System components Structure
Let's explain first the system structure to understand its components:
```
SkyPay-hotel-system --> Parent folder. 
|- infrastructures
  |- config-server --> Service discovery server
  |- eureka-server --> Centralized Configuration server
  |- spring-admin-server --> Spring Admin for metrics and logs
|-modules
  |- api-gateway --> API Gateway server
  |- user-service --> User management service
  |- room-service --> Room management service
  |- booking-service --> Booking service
  |- payment-service-mock --> Mocking a Payment service
|-api-common 
  |- api --> api shared between services 
|- docker-compose.yml --> contains all services
```
![conception](https://github.com/user-attachments/assets/f1ec2a1d-8ef6-4ac7-a4db-d15d779ee926)

Now, as we have learned about different system components, then let's start.

### Running Them All
Now it's the time to run all of our  Microservices, and it's straightforward just run the following `docker-compose` commands:
```
docker-compose up
```
![docker](https://github.com/user-attachments/assets/d8f124fa-4238-4a66-816b-f1d3684eba80)

### Access Service Discovery Server (Eureka)
If you would like to access the Eureka service discovery point to this URL [http://localhosts:8761/eureka/web](https://localhost:8761/eureka/web) to see all the services registered inside it.

![eureka](https://github.com/user-attachments/assets/cb49509d-9fd5-4d8b-8bbe-95afc36f77bc)

### Access Zipkin
You can manually check traceId flow between microservices using zipkin UI at this URL [http://localhosts:9411/zipkin](https://localhost:9411/zipkin)

![zipkin](https://github.com/user-attachments/assets/eaa6a05f-6cf2-42b7-aada-be522d043ed0)

### Access Spring boot admin
You can check the dashboard of spring admin for metrics and logs at this URL [http://localhosts:7070/admin](https://localhost:7070/admin)

![spring-admin](https://github.com/user-attachments/assets/b5b27205-c6ab-4274-8e83-ad5dbee2ec9e)

### API Documentation – OpenAPI
The backend microservices are documented using OpenAPI for easy exploration and testing of API endpoints.

![swagger](https://github.com/user-attachments/assets/960e095b-a4af-4501-ba5a-bf95ac0d04e4)

### Docker Hub Repository
All application services, including frontend and backend microservices, are containerized and available publicly on Docker Hub:
[https://hub.docker.com/u/med29](https://hub.docker.com/u/med29)

Each image is tagged as latest and structured for seamless deployment using Docker Compose:
```
med29/skypay-hotel-ui – React frontend
med29/skypay-hotel-api-gateway
med29/skypay-hotel-user-service
med29/skypay-hotel-room-service
med29/skypay-hotel-booking-service
med29/skypay-hotel-payment-service-mock
med29/skypay-hotel-eureka-server
med29/skypay-hotel-config-server
med29/skypay-hotel-spring-admin-server
```
These images are integrated into the Docker Compose setup to enable full local deployment and testing with a single command.

## Testing Skypay Hotel Project

### Cloning It

The first thing to do is to open **git bash** command line, and then simply you can clone the project under any of your favorite places as the following:

```bash
> git clone https://github.com/Amine-er/SkyPay-hotel-system.git
> git checkout staging
> docker-compose up
```
### Mock Payment Strategy

To simulate real-world payment processing in a test environment, this project uses a mock payment service. Instead of integrating with a third-party payment gateway (such as Stripe or PayPal), we use a predefined mock user with valid-looking card details and an initial balance to validate payment transactions.

| Field         | Value                 |
| ------------- | --------------------- |
| ID            | `1`                   |
| Full Name     | `Mohamed Amine`       |
| Card Number   | `4111 1111 1111 1111` |
| Expiry Date   | `2028-12-31`          |
| CVV           | `123`                 |
| Balance (MAD) | `25000.00`            |

### Frontend UI
Below are some UI previews from the hotel reservation system's frontend, built with React,Vite, Tailwind CSS, and shadcn/ui. The user interface is designed to be clean, responsive, and user-friendly.

Repo : [https://github.com/Amine-er/SkyPay-hotel-system-ui](https://github.com/Amine-er/SkyPay-hotel-system-ui)

- Browse and filter available hotel rooms.
  
![UI-1](https://github.com/user-attachments/assets/167030fc-27c7-43d0-9e14-3bfe7079c234)

- Confirmation popup with reservation reference after a successful payment.
  
![UI-2](https://github.com/user-attachments/assets/e845b354-1713-499c-b0c1-98d5ca3e490c)

### Next version :
In the next version I'll continue the dev with the following fixes :
-  GRPC for synchron communication between microservices
-  Add Unit test using JUnit and mockito
-  Adding keycloak for login using JWT
-  Send email for success reservation with the reference ID
-  Add security filter in the API-Gateway to secure the API
-  Add redis for cache management
-  Change the microservice user-service to customer-service for customer management
-  Add connect the room-service with an object store minaio to fetch room photos
-  Try to deploy it in kubernetes
