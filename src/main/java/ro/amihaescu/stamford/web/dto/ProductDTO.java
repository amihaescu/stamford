package ro.amihaescu.stamford.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {

    private String name;
    private double price;
}
