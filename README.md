# Distributed Locking (Redlock) Live Lab

Run: `mvn spring-boot:run`
Acquire: `curl -X POST "http://localhost:8080/api/v1/lock/acquire?resource=ORDER-1&owner=pod-A"`
