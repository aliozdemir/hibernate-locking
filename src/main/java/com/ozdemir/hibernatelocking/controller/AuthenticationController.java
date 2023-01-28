package com.ozdemir.hibernatelocking.controller;

import com.ozdemir.hibernatelocking.model.request.AuthenticationRequest;
import com.ozdemir.hibernatelocking.model.response.AuthenticationResponse;
import com.ozdemir.hibernatelocking.service.AuthenticationService;
import com.ozdemir.hibernatelocking.model.request.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "This service is available for all users")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request){
        authenticationService.register(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "This service is available for all users")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }

}
