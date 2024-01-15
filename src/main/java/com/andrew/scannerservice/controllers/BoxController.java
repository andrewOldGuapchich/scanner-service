package com.andrew.scannerservice.controllers;

import com.andrew.scannerservice.services.BoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/box/inform")
public class BoxController {
    private final BoxService boxService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getInform(
            @PathVariable Long id){
        return boxService.getListProduct(id);
    }
}
