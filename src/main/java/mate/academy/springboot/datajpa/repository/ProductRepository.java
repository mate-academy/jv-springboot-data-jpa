package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    @Query("FROM Product p WHERE p.category.name IN (?1)")
    List<Product> findAllByCategory(List<String> categories);
}
