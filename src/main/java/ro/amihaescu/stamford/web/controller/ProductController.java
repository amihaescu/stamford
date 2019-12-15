package ro.amihaescu.stamford.web.controller;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.amihaescu.stamford.repository.ProductRepository;
import ro.amihaescu.stamford.web.dto.ProductDTO;
import ro.amihaescu.stamford.web.mapper.ProductMapper;

@RestController
@RequestMapping("product")
@Log
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductController(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductDTO productDTO) {
        log.info("Creating product");
        productRepository.save(productMapper.toEntity(productDTO));
        return  ResponseEntity.ok().build();
    }
}
