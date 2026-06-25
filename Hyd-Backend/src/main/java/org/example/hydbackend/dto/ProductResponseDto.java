package org.example.hydbackend.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String name;
    private String discription;
    private Double price;
    private String mainImageUrl;
}
