package mate.academy.springboot.datajpa.repository;

import mate.academy.springboot.datajpa.models.Category;
import mate.academy.springboot.datajpa.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    @Query("FROM Product p left join fetch p.category WHERE p.category IN :categories")
    List<Product> getByCategories(List<Category> categories);
}
