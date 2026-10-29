# Distributed Rate Limiting Live Application

Run: `mvn spring-boot:run`
Spam resource: `for i in {1..7}; do curl -i http://localhost:8080/api/v1/throttle/resource; done`
