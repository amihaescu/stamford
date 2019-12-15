package ro.amihaescu.stamford.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private LocalDateTime created = LocalDateTime.now();
    @Builder.Default
    private Boolean deleted = false;
}
