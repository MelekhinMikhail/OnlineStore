package com.example.onlinestore.dto.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateProductDto {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private Integer quantityInStock;
    private Long userId;
}
