package org.example.hydbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {

    private String name;
    private String description;
    private Double price;
}
