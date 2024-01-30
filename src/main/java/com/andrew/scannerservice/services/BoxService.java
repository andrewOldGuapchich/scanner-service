package com.andrew.scannerservice.services;

import com.andrew.scannerservice.dao.BoxDAO;
import com.andrew.scannerservice.model.dtos.BoxDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class BoxService {
    private final BoxDAO boxDAO;

    public ResponseEntity<?> getListProduct(long idBox){
        try {
            if(boxDAO.getBoxInformList(idBox).isEmpty())
                return ResponseEntity.status(400).body("Ящик не найден!");
            else
                return ResponseEntity.ok(boxDAO.getBoxInformList(idBox));
        } catch (SQLException sqlException){
            return ResponseEntity.status(500).body("Ошибка при обращении!");
        }
    }

    public ResponseEntity<?> getBoxData(Long idBox){
        try {
            BoxDto box = boxDAO.getBoxData(idBox);
            if(box != null) {
                box.setDate(getFormattedDate(box.getDate()));
            }
            return box == null ? ResponseEntity.status(400).body("Ящик не найден!") : ResponseEntity.ok(box);
        } catch (SQLException sqlException){
            return ResponseEntity.status(500).body("Ошибка при обращении!");
        }
    }

    private String getFormattedDate(String inDate){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(inDate, inputFormatter);
        return date.format(outputFormatter);
    }
}
