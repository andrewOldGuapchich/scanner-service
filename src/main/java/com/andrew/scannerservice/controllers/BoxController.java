package com.andrew.scannerservice.controllers;

import com.andrew.scannerservice.services.BoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/box")
public class BoxController {
    private final BoxService boxService;
    @GetMapping("/inform/{id}")
    public ResponseEntity<?> getProductOnBox(
            @PathVariable Long id){
        return boxService.getListProduct(id);
    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity<?> getInformBox(
            @PathVariable Long id) {

    }*/
}
