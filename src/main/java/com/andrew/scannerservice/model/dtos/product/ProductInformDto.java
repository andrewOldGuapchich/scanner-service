package com.andrew.scannerservice.model.dtos.product;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductInformDto {
    private long boxId;
    private int count;

    //private String type;
}
