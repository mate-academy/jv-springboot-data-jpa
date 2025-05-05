package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetween(BigDecimal lowest, BigDecimal highest);

    @Query(value = "SELECT * FROM products p WHERE p.category_id IN (?1)", nativeQuery = true)
    List<Product> findAllByCategoryIn(List<String> categories);
}
