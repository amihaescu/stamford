package ro.amihaescu.stamford.web.mapper;

import org.springframework.stereotype.Component;
import ro.amihaescu.stamford.model.Product;
import ro.amihaescu.stamford.web.controller.ProductController;
import ro.amihaescu.stamford.web.dto.ProductDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class ProductMapper {

    public Product toEntity(ProductDTO productDTO) {
        return Product.builder().name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
    }

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = ProductDTO.builder().name(product.getName())
                .price(product.getPrice())
                .created(product.getCreated())
                .build();
        productDTO.add(linkTo(ProductController.class).slash(product.getId()).withSelfRel());
        productDTO.add(linkTo(ProductController.class).withRel("all products"));
        return productDTO;
    }
}
