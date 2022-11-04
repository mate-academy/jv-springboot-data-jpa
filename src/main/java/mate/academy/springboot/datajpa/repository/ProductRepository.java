package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    @Query(value = "FROM Product p JOIN FETCH p.category WHERE p.category = ?1")
    List<Product> getAllByCategory(Category category);
}
