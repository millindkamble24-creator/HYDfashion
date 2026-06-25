package org.example.hydbackend.controller;

import org.example.hydbackend.entity.Product;
import org.example.hydbackend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/upload/productdetails")
@CrossOrigin(origins = "http://localhost:5173")

public class UploadController {

    private ProductService productService;

    public UploadController(ProductService productService){
        this.productService=productService;
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam MultipartFile image) throws IOException{

        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Price: " + price);
        System.out.println("Image: " + image.getOriginalFilename());

        Product product=productService.saveProduct(name,description,price,image);
        return ResponseEntity.ok(product);
    }


}
