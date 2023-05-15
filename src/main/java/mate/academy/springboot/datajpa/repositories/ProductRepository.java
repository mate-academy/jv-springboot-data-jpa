package mate.academy.springboot.datajpa.repositories;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {
    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    @Query("FROM Product p join fetch Category c WHERE c.name IN(?1)")
    List<Product> findAllByCategories(List<String> categories);
}
