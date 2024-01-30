package com.andrew.scannerservice.services;

import com.andrew.scannerservice.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;

    public ResponseEntity<?> getProductInform(long productId){
        try{
            if(productDAO.getProductList(productId).isEmpty())
                return ResponseEntity.status(400).body("Товар не найден!");
            else
                return ResponseEntity.ok(productDAO.getProductList(productId));
        } catch (SQLException sqlException){
            return ResponseEntity.status(500).body("Ошибка при обращении!");
        }
    }
}
