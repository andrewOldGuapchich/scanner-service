package com.andrew.scannerservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/start")
public class StartController {
    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.ok("ok!");
    }
}
