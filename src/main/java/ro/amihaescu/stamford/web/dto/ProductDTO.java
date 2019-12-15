package ro.amihaescu.stamford.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDateTime;


@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ProductDTO extends ResourceSupport {
    private String name;
    private double price;
    @EqualsAndHashCode.Exclude
    private LocalDateTime created;
}
