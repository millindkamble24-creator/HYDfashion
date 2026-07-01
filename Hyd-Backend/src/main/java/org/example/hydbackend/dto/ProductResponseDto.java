package org.example.hydbackend.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hydbackend.entity.Product;

@Setter
@Getter
@NoArgsConstructor
public class ProductResponseDto {


    private Long id;
    private String name;
    private String discription;
    private Double price;
    private String category;
    private String mainImageUrl;



    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.discription=product.getDescription();
        this.price = product.getPrice();
        this.category=product.getCategory();
        this.mainImageUrl = product.getMainImageUrl();
    }
}
