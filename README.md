# GraphQL with Spring Boot & Netflix DGS Live Application

Demonstrates:
- **Declarative Schema-First Design**: `schema.graphqls` strongly typed contracts.
- **Nested Schema Resolvers**: Resolving `Book -> Author` dynamically via `@SchemaMapping`.
- **Interactive GraphiQL IDE**: In-browser GraphQL exploration playground.

## Running the Application
```bash
mvn spring-boot:run
```

## Interactive GraphiQL IDE
Open `http://localhost:8080/graphiql` in your browser to run interactive queries!

## Live Testing with cURL
```bash
# Query book and author with GraphQL
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{"query": "{ bookById(id: \"book-1\") { title author { name nationality } } }"}'
```

## Automated Testing
```bash
mvn test
```
