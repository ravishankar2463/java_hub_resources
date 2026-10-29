# High-Performance gRPC in Java Live Application

Demonstrates:
- **Binary Protobuf Wire Serialization**: Extreme compactness and zero JSON overhead.
- **HTTP/2 Transport Framing**: Sub-millisecond Remote Procedure Call (RPC) latency.

## Running the Application
```bash
mvn spring-boot:run
```

## Live Testing with cURL
```bash
# 1. Encode order to binary Protobuf frame
curl -X POST "http://localhost:8080/api/v1/grpc/simulate-encode?orderId=501&symbol=AMZN&price=185.20&quantity=50"
```

## Automated Testing
```bash
mvn test
```
