package com.javahub.grpc.controller;

import com.javahub.grpc.codec.ProtobufBinaryCodec;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/grpc")
public class GrpcInspectionController {

    private final ProtobufBinaryCodec codec = new ProtobufBinaryCodec();

    @PostMapping("/simulate-encode")
    public ResponseEntity<Map<String, Object>> encodeOrder(
            @RequestParam long orderId,
            @RequestParam String symbol,
            @RequestParam double price,
            @RequestParam int quantity) {
        var frame = new ProtobufBinaryCodec.OrderWireFrame(orderId, symbol, price, quantity);
        byte[] binary = codec.serialize(frame);
        String base64 = Base64.getEncoder().encodeToString(binary);

        return ResponseEntity.ok(Map.of(
            "wireBytesLength", binary.length,
            "base64Payload", base64,
            "status", "SERIALIZED"
        ));
    }

    @PostMapping("/simulate-decode")
    public ResponseEntity<ProtobufBinaryCodec.OrderWireFrame> decodeOrder(@RequestBody Map<String, String> body) {
        byte[] binary = Base64.getDecoder().decode(body.get("base64Payload"));
        var frame = codec.deserialize(binary);
        return ResponseEntity.ok(frame);
    }
}
