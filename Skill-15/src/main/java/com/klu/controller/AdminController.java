package com.klu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/add")
    public ResponseEntity<String> addResource() {
        return ResponseEntity.ok("Admin resource added successfully.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteResource() {
        return ResponseEntity.ok("Admin resource deleted successfully.");
    }
}
