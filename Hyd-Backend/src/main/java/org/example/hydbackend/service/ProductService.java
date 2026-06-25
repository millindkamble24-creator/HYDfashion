package org.example.hydbackend.service;

import org.example.hydbackend.dto.ProductResponseDto;
import org.example.hydbackend.entity.Product;
import org.example.hydbackend.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private S3Service s3Service;

    public ProductService(ProductRepository productRepository,S3Service s3Service){
        this.productRepository=productRepository;
        this.s3Service=s3Service;
    }

    public Product saveProduct(
            String name, String description, Double price, MultipartFile image) throws IOException {

        String imageUrl = s3Service.uploadFile(image);
        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setMainImageUrl(imageUrl);

        return productRepository.save(product);
    }

    public Page<Product> getAllProducts(int page,int size)
    {
        Pageable bringpageable= PageRequest.of(page,size);
       // Page<Product> productPage=productRepository.findAll(pageable);
        return productRepository.findAll(bringpageable);
    }

    public Product getdetailsbyid(Long id){

        return productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("file not found"));
    }

}
