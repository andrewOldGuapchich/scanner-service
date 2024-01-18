package com.andrew.scannerservice.services;

import com.andrew.scannerservice.dao.BoxDAO;
import com.andrew.scannerservice.model.dtos.BoxDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class BoxService {
    private final BoxDAO boxDAO;

    public ResponseEntity<?> getListProduct(long idBox){
        try {
            if(boxDAO.getBoxInformList(idBox).isEmpty())
                return ResponseEntity.ok("Ящик пустой!");
            else
                return ResponseEntity.ok(boxDAO.getBoxInformList(idBox));
        } catch (SQLException sqlException){
            return ResponseEntity.status(500).body("Ошибка при обращении!");
        }
    }

    public ResponseEntity<?> getBoxData(Long idBox){
        try {
            BoxDto box = boxDAO.getBoxData(idBox);
            return box == null ? ResponseEntity.ok("Ящик не найден!") : ResponseEntity.ok(box);
        } catch (SQLException sqlException){
            return ResponseEntity.status(500).body("Ошибка при обращении!");
        }
    }

}
