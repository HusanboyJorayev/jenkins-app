package org.example.jenkins.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    @Cacheable(value = "test", key = "#id")
    public ResponseEntity<?> getTest(Long id) {
        return ResponseEntity.ok().body("Hello " + id);
    }

}
