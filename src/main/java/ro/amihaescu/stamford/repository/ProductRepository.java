package ro.amihaescu.stamford.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.amihaescu.stamford.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
