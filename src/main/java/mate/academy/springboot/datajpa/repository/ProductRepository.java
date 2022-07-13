package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("FROM Product p WHERE p.category.id IN :categoriesIds AND "
            + "(p.price BETWEEN :from AND :to OR :from IS NULL AND :to IS NULL)")
    List<Product> findProductsByCategoryIdInAndPriceBetween(
            @Param("categoriesIds") List<Long> categoriesIds,
            @Param("from") BigDecimal from,
            @Param("to") BigDecimal to);
}
