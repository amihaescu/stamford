package ro.amihaescu.stamford.web.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.amihaescu.stamford.model.Product;
import ro.amihaescu.stamford.web.dto.ProductDTO;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductMapperTest {

    private ProductMapper productMapper;

    @Before
    public void setUp() {
        productMapper = new ProductMapper();
    }

    @Test
    public void toEntity() {
        ProductDTO productDTO = ProductDTO.builder().name("Beer").price(100.00).build();
        Product expectedProduct = Product.builder().name("Beer").price(100.00).build();
        assertEquals(expectedProduct, productMapper.toEntity(productDTO));
    }

    @Test
    public void toDTO() {
        Product product = Product.builder().name("Beer").price(100.00).build();
        ProductDTO expectedDTO = ProductDTO.builder().name("Beer").price(100.00).build();
        assertEquals(expectedDTO, productMapper.toDTO(product));
    }
}