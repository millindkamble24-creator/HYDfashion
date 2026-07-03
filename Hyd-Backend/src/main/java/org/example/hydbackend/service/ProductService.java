package org.example.hydbackend.service;

import org.example.hydbackend.dto.CursorResponseDto;
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
            String name, String description, Double price,String category, MultipartFile image) throws IOException {

        String imageUrl = s3Service.uploadFile(image);
        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setMainImageUrl(imageUrl);

        return productRepository.save(product);
    }

    public CursorResponseDto getProducts(Long cursor){
        List<Product> products;
        if(cursor==null){
            products=productRepository.findTop11ByOrderByIdAsc();
        }
        else{
            products=productRepository.findTop11ByIdGreaterThanOrderByIdAsc(cursor);
        }
        boolean hasNext=products.size()>10;
        if(hasNext){
            products.remove(10);
            List<ProductResponseDto> response = products.stream()
                    .map(ProductResponseDto::new)
                    .toList();
        }
        List<ProductResponseDto> response=
                products.stream()
                        .map(ProductResponseDto::new)
                        .toList();

        Long nextCursor=null;

        if(!products.isEmpty()){
            nextCursor=products.get(products.size()-1).getId();
        }
        return new CursorResponseDto(
          response,nextCursor,hasNext
        );
    }

    public Product getdetailsbyid(Long id){

        return productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("file not found"));
    }

    public List<Product> getproductsbycategory(String category){
        return productRepository.findAllByCategory(category);
    }
}
