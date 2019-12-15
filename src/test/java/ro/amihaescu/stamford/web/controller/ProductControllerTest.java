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
import ro.amihaescu.stamford.repository.ProductRepository;
import ro.amihaescu.stamford.web.dto.ProductDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void createProduct() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(ProductDTO.builder().name("Beer").price(100.00).build())))
                .andExpect(status().isOk());

        assertEquals(1, productRepository.findAll().size());

    }

}
