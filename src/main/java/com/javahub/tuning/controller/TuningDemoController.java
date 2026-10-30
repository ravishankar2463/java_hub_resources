package com.javahub.tuning.controller;
import com.javahub.tuning.dto.UserSummaryProjection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/tuning")
public class TuningDemoController {
    @GetMapping("/projections")
    public ResponseEntity<List<UserSummaryProjection>> getProjections() {
        return ResponseEntity.ok(List.of(
            new UserSummaryProjection(1L, "user1@corp.com"),
            new UserSummaryProjection(2L, "user2@corp.com")
        ));
    }
}
