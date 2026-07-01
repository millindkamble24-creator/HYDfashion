package org.example.hydbackend.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name ="description", length = 2000)
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name="category")
    private String category;

    @Column(name = "mainImageUrl")
    private String mainImageUrl;
}
