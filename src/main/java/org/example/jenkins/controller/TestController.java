package org.example.jenkins.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {


    @GetMapping("/get")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("TEST IS WORKING ON SERVER");
    }

    @GetMapping("/me")
    public ResponseEntity<?> me() {
        return ResponseEntity.ok("JO'RAYEV HUSANBOY");
    }
}
