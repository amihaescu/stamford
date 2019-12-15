package ro.amihaescu.stamford.web.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.amihaescu.stamford.model.Product;
import ro.amihaescu.stamford.web.dto.ProductDTO;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        productMapper = new ProductMapper();
    }

    @Test
    void toEntity() {
        ProductDTO productDTO = ProductDTO.builder().name("Beer").price(100.00).build();
        Product expectedProduct = Product.builder().name("Beer").price(100.00).build();
        assertEquals(expectedProduct, productMapper.toEntity(productDTO));
    }

    @Test
    void toDTO() {
        Product product = Product.builder().name("Beer").price(100.00).build();
        ProductDTO expectedDTO = ProductDTO.builder().name("Beer").price(100.00).build();
        assertEquals(expectedDTO, productMapper.toDTO(product));
    }
}