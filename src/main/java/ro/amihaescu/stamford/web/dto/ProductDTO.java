package ro.amihaescu.stamford.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ProductDTO extends ResourceSupport {
    @NotNull
    private String name;
    @NotNull
    private double price;
    @EqualsAndHashCode.Exclude
    private LocalDateTime created;
}
