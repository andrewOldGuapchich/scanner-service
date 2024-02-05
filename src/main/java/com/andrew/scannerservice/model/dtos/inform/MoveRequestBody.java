package com.andrew.scannerservice.model.dtos.inform;

import lombok.Data;

@Data
public class MoveRequestBody {
    private long idBoxFrom;
    private long idBoxTo;
    private long idProduct;
    private int count;
}
