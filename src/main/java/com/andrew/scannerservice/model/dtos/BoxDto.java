package com.andrew.scannerservice.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BoxDto {
    private int id;
    private int group;
    private int number;
    private Date date;
}
