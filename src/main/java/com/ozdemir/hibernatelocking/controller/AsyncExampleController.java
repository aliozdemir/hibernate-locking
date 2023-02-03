package com.ozdemir.hibernatelocking.controller;

import com.ozdemir.hibernatelocking.model.response.AsyncResponse;
import com.ozdemir.hibernatelocking.service.async.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/async")
@RequiredArgsConstructor
public class AsyncExampleController {

    private final AsyncService asyncService;

    @GetMapping("/test")
    public ResponseEntity<AsyncResponse> test(){
        return ResponseEntity.ok(asyncService.test());
    }
}
