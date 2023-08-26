package com.example.onlinestore.dto.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductDto {

    private String title;
    private String description;
    private Double price;
    private Integer quantityInStock;
}
