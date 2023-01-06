package mate.academy.springboot.datajpa.repository;

import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);
}
