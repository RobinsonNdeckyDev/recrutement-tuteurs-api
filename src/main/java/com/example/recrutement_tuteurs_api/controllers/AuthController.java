package com.example.recrutement_tuteurs_api.controllers;

import com.example.recrutement_tuteurs_api.models.AuthenticationRequest;
import com.example.recrutement_tuteurs_api.models.AuthenticationResponse;
import com.example.recrutement_tuteurs_api.models.RegisterRequest;
import com.example.recrutement_tuteurs_api.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request,
            @RequestParam(required = false) String source,
            @RequestParam(required = false) String locale
    ) {
        logger.info("Source: {}, Locale: {}", source, locale);
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
}
