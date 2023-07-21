package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getAllProductsByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllProductsByCategoryNameIn(Collection<String> categoriesName);
}
