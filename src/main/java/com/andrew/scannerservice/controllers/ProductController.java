package com.andrew.scannerservice.controllers;

import com.andrew.scannerservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getList(@PathVariable Long id){
        return productService.getProductInform(id);
    }
}
