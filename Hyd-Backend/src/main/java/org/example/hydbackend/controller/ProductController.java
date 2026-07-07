package org.example.hydbackend.controller;


import org.example.hydbackend.dto.CursorResponseDto;
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
    public CursorResponseDto getProducts(@RequestParam(required = false)Long cursor){
        return productService.getProducts(cursor);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){

        return productService.getdetailsbyid(id);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category){
        return productService.getproductsbycategory(category);
    }

    @GetMapping("/products/search")
    public List<Product> getProductBykeywordorcategory(@RequestParam(required = false) String keyword,@RequestParam(required = false) String category){
        if(keyword !=null && keyword.isBlank()){
            keyword=null;
        }
        if(category !=null && category.isBlank()){
            category=null;
        }
        return productService.getproductsbykeywordorcategory(keyword,category);
    }

}
