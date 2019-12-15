package ro.amihaescu.stamford.web.controller;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.amihaescu.stamford.model.Product;
import ro.amihaescu.stamford.repository.ProductRepository;
import ro.amihaescu.stamford.web.dto.ProductDTO;
import ro.amihaescu.stamford.web.exception.ProductNotFoundException;
import ro.amihaescu.stamford.web.mapper.ProductMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> allProducts = productRepository.findAll().stream().map(productMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(allProducts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO updatedProduct) throws ProductNotFoundException {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
        Product product = productMapper.toEntity(updatedProduct);
        product.setId(id);
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id.toString()));
        product.setDeleted(true);
        productRepository.save(product);
        return  ResponseEntity.ok().build();
    }
}
