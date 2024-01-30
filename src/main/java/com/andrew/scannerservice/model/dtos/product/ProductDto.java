package com.andrew.scannerservice.model.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductDto {
    private String name;
    private List<ProductInformDto> productInforms;
}
