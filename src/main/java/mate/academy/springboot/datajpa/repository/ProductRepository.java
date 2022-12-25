package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetween(BigDecimal fromPrice, BigDecimal toPrice);

    List<Product> findAllByCategoryIn(Collection<Category> categories);
}
