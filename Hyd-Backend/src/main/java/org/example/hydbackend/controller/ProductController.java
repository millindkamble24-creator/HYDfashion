package org.example.hydbackend.controller;


import org.example.hydbackend.entity.Product;
import org.example.hydbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public Page<Product> getAllproducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12")int size){
        return productService.getAllProducts(page,size);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){

        return productService.getdetailsbyid(id);
    }

}
