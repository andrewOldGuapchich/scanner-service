package com.andrew.scannerservice.model.entity;

import lombok.Data;

@Data
public class Box {
    private long id;
    private int group;
    private int number;
    private String date;
}
