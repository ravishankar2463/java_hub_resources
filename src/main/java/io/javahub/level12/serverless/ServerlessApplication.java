package io.javahub.level12.serverless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.function.Function;

@SpringBootApplication
public class ServerlessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerlessApplication.class, args);
    }

    public record OrderRequest(String customerId, double amount) {}
    public record OrderResponse(String orderId, String status, double processedAmount, long timestamp) {}

    @Bean
    public Function<OrderRequest, OrderResponse> processOrderFunction() {
        return request -> new OrderResponse(
                UUID.randomUUID().toString(),
                "APPROVED",
                request.amount(),
                System.currentTimeMillis()
        );
    }
}
