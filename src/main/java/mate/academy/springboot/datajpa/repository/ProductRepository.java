package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaSpecificationExecutor<Product>,
        JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetween(BigDecimal fromPrice, BigDecimal toPrice);
}
