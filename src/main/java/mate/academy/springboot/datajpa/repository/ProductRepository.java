package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    @Query("UPDATE Product p SET p.price = ?1, p.title = ?2, p.category = ?3 WHERE p.id = ?4")
    Product update(BigDecimal price, String title, Long categoryId, Long productId);
}
