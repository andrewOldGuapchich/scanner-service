package com.andrew.scannerservice.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoxInformDto {
    private long productId;
    private int count;
    private String description;
}
