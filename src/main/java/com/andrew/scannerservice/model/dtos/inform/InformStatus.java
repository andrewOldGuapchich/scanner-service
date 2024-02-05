package com.andrew.scannerservice.model.dtos.inform;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InformStatus {
    private int status;
    private String message;
}
