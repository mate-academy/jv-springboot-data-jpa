package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getProductsByPriceBetween(BigDecimal from, BigDecimal to);

    @Query("SELECT p FROM Product p WHERE p.category IN p.category")
    List<Product> getProductsByCategoryIn(List<Long> categoryIds);
}
