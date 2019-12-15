package ro.amihaescu.stamford.web.mapper;

import org.springframework.stereotype.Component;
import ro.amihaescu.stamford.model.Product;
import ro.amihaescu.stamford.web.dto.ProductDTO;

@Component
public class ProductMapper {

    public Product toEntity(ProductDTO productDTO) {
        return Product.builder().name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
    }

    public ProductDTO toDTO(Product product) {
        return ProductDTO.builder().name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
