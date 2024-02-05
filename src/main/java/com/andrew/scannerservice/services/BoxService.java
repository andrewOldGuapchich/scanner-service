package com.andrew.scannerservice.services;

import com.andrew.scannerservice.dao.BoxDAO;
import com.andrew.scannerservice.model.dtos.box.BoxDto;
import com.andrew.scannerservice.model.dtos.inform.InformStatus;
import com.andrew.scannerservice.model.dtos.inform.MoveRequestBody;
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

    public ResponseEntity<?> moveProduct(MoveRequestBody body){
        boolean isDel = isDelete(body.getIdBoxFrom(), body.getIdProduct(), body.getCount());
        int currentCount = boxDAO.getCountProductOnBox(body.getIdBoxTo(), body.getIdProduct()); //сколько в ящике сейчас
        if(!isDel) {
            InformStatus inform = new InformStatus(400, "В ящике " +
                    body.getIdBoxFrom() + " мало " + body.getIdProduct() + "!\n" +
                    currentCount +
                    " штук.");
            return ResponseEntity.badRequest().body(inform);
        } else {
            addOnBox(body.getIdBoxTo(), body.getIdProduct(), body.getCount());
            return ResponseEntity.ok(new InformStatus(200, "Перемещение: из " + body.getIdBoxFrom() + " в "
                + body.getIdBoxTo() + " " + body.getCount() + " штук."));
        }
    }

    private boolean isDelete(long idBox, long idProduct, int count){
        int currentCount = boxDAO.getCountProductOnBox(idBox, idProduct); //сколько в ящике сейчас
        System.out.println(currentCount);
        if(count > currentCount) {
            return false;
        } else if(count == currentCount){
            boxDAO.clearBox(idBox, idProduct);
        } else {
            boxDAO.moveOnFromBox(idBox, idProduct, count);
        }
        return true;
    }

    private void addOnBox(long idBox, long idProduct, int count){
        boxDAO.moveOnToBox(idBox, idProduct, count);
    }

    private String getFormattedDate(String inDate){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(inDate, inputFormatter);
        return date.format(outputFormatter);
    }
}
