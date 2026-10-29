package com.javahub.realtime.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/v1/stream")
public class SseStockTickerController {

    private final CopyOnWriteArrayList<SseEmitter> activeEmitters = new CopyOnWriteArrayList<>();

    @GetMapping(value = "/prices", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeToPriceStream() {
        SseEmitter emitter = new SseEmitter(60_000L); // 60 seconds timeout
        activeEmitters.add(emitter);

        emitter.onCompletion(() -> activeEmitters.remove(emitter));
        emitter.onTimeout(() -> activeEmitters.remove(emitter));
        emitter.onError(e -> activeEmitters.remove(emitter));

        try {
            emitter.send(SseEmitter.event().name("CONNECT").data(Map.of("message", "Connected to Real-time SSE Stock Feed")));
        } catch (IOException ex) {
            emitter.completeWithError(ex);
        }

        return emitter;
    }

    @PostMapping("/broadcast")
    public ResponseEntity<Map<String, Object>> broadcastPriceUpdate(
            @RequestParam String ticker,
            @RequestParam double price) {
        Map<String, Object> update = Map.of(
            "ticker", ticker,
            "price", price,
            "timestamp", System.currentTimeMillis()
        );

        int deliveredCount = 0;
        for (SseEmitter emitter : activeEmitters) {
            try {
                emitter.send(SseEmitter.event().name("PRICE_TICK").data(update));
                deliveredCount++;
            } catch (IOException e) {
                emitter.complete();
                activeEmitters.remove(emitter);
            }
        }

        return ResponseEntity.ok(Map.of("status", "BROADCAST_COMPLETED", "recipients", deliveredCount));
    }
}
