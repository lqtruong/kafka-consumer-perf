package com.turong.training.rest.controller.ping;

import com.turong.training.rest.kafka.PingSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("async")
@Slf4j
public class AsyncController {

    @Autowired
    private PingSender pingSender;

    @PostMapping("/ping")
    public ResponseEntity<String> ping(@RequestBody PingRequest request) {
        log.info("ping={}", request);
        pingSender.send(request.getTimes(), request.getValue());
        return ResponseEntity.ok("Success");

    }

    @PostMapping("/ping/custom-header")
    public ResponseEntity<String> pingWithCustomHeader(@RequestBody PingRequest request) {
        log.info("ping={}", request);
        pingSender.sendWithCustomHeader(request.getTimes(), request.getValue());
        return ResponseEntity.ok("Success");

    }

}
