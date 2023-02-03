package com.ozdemir.hibernatelocking.controller;

import com.ozdemir.hibernatelocking.model.entity.User;
import com.ozdemir.hibernatelocking.model.response.UserResponse;
import com.ozdemir.hibernatelocking.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "This service is available to users who has ADMIN role")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.ok(userService.getUsers(0, true));
    }
}
