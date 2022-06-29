package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query("update Product p set p.title = ?1, p.category = ?2, p.price = ?3 where p.id = ?4")
    Product updateProductById(String title, BigDecimal price, Category category, Long id);

    List<Product> findAllByPriceBetween(BigDecimal lowerBound, BigDecimal upperBound);

    @Query("FROM Product p JOIN FETCH p.category WHERE p.category.id IN ?1")
    List<Product> getAllByCategory(List<Long> categoryIds);
}
