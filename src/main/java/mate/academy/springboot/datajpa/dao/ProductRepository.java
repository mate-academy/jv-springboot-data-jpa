package mate.academy.springboot.datajpa.dao;

import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findProductsByCategoryNameIn(List<String> categories);
}
