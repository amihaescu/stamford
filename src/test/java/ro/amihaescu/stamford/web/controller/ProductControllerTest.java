package ro.amihaescu.stamford.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ro.amihaescu.stamford.model.Product;
import ro.amihaescu.stamford.repository.ProductRepository;
import ro.amihaescu.stamford.web.dto.ProductDTO;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    private static ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void initializeObjectMapper() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    public void clearDatabase() {
        productRepository.deleteAll();
    }

    @Test
    public void successfullyCreateProduct() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(ProductDTO.builder().name("Beer").price(100.00).build())))
                .andExpect(status().isOk());

        assertEquals(1, productRepository.findAll().size());
    }

    @Test
    public void successfullyGetAllProducts() throws Exception {
        productRepository.saveAll(Arrays.asList(
                Product.builder().name("Coke").price(5.00).deleted(true).build(),
                Product.builder().name("Water").price(4.50).build()
        ));

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.[0].name").isString())
                .andExpect(jsonPath("$.[0].name").value("Water"))
                .andExpect(jsonPath("$.[0].price").isNumber())
                .andExpect(jsonPath("$.[0].price").value(4.50));
    }

    @Test
    public void successfullyUpdate_ExistingProduct() throws Exception {
        Product savedProduct = productRepository.save(Product.builder().price(5.00).name("Coca Cola").build());
        ProductDTO updatedProduct = ProductDTO.builder().name("Coca Cola Light").price(5.00).build();

        mockMvc.perform(
                put("/product/{id}", savedProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Coca Cola Light"))
                .andExpect(jsonPath("$.price").value(5.00));
    }

    @Test
    public void failToUpdate_ProductNotFound() throws Exception {
        ProductDTO updatedProduct = ProductDTO.builder().name("Coca Cola Light").price(5.00).build();

        MvcResult mvcResult = mockMvc.perform(
                put("/product/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isNotFound())
                .andReturn();

        assertNotNull(mvcResult.getResolvedException());
        assertEquals("Product with id 1 not found", mvcResult.getResolvedException().getMessage());
    }

    @Test
    public void successfullyDelete_ExistingProduct() throws Exception {
        Product savedProduct = productRepository.save(Product.builder().price(5.00).name("Coca Cola").build());

        mockMvc.perform(
                delete("/product/{id}", savedProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        Optional<Product> deletedProduct = productRepository.findById(savedProduct.getId());
        assertTrue(deletedProduct.isPresent());
        assertTrue(deletedProduct.get().getDeleted());
    }

    @Test
    public void failToDelete_ProductNotFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                delete("/product/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andReturn();

        assertNotNull(mvcResult.getResolvedException());
        assertEquals("Product with id 1 not found", mvcResult.getResolvedException().getMessage());
    }

}
