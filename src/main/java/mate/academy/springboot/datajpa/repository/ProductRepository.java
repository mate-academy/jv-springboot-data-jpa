package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product category);

    Optional<Product> findById(Long id);

    void deleteById(Long id);

    List<Product> getAllByPriceIsBetween(BigDecimal start, BigDecimal end);

    List<Product> getAllByCategoryIdIsIn(List<Long> categories);
}
