package ro.amihaescu.stamford.web.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;


@Data
@Builder
public class ProductDTO extends RepresentationModel<ProductDTO> {

    private String name;
    private double price;
    private LocalDateTime created;
}
