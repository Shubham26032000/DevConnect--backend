package com.devconnect.api_gateway.controller;

import com.devconnect.api_gateway.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final WebClient webClient;
    private final JwtUtil jwtUtil;

    public AuthController(WebClient.Builder builder, JwtUtil jwtUtil) {
        this.webClient = builder.build();
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, String>>> login(@RequestBody Map<String, String> body) {
        System.out.println("Inside Response of API Gateway");
        return webClient.post()
                .uri("http://user-service/user/login")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {

                    Long userId = Long.valueOf(response.get("userId").toString());
                    String username = response.get("username").toString();

                    String token = jwtUtil.generateToken(userId, username);

                    return ResponseEntity.ok(Map.of(
                            "token", token
                    ));
                });
    }
}
