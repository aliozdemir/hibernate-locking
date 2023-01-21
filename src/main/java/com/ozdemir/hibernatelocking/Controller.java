package com.ozdemir.hibernatelocking;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class Controller {

    private final TestService testService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Campaign campaign){
        testService.save(campaign);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id){
        testService.update(id);
        return ResponseEntity.ok(null);
    }
}
