# Spring Data JPA & Hibernate Live Application

Features:
- **JPA Bidirectional Mapping**: `Customer` and `CustomerOrder` with managed collection relationships.
- **Solving N+1 Query Problem**: Clean query generation using `@EntityGraph`.
- **In-Memory H2 Database**: Preloaded with seed fixtures and interactive web console.

## Running the Application
```bash
mvn spring-boot:run
```

## Interactive H2 Database Console
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:customerdb`
- User: `sa` (password empty)

## Live Testing with cURL
```bash
# 1. Fetch active customers
curl -X GET http://localhost:8080/api/v1/customers/active

# 2. Fetch customer with eagerly loaded orders (no N+1 query)
curl -X GET http://localhost:8080/api/v1/customers/1/orders
```

## Automated Testing
```bash
mvn test
```
