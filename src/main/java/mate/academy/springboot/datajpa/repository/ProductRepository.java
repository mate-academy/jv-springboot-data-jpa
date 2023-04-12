package mate.academy.springboot.datajpa.repository;

import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo);
    List<Product> findAllByCategoryIdIn(Collection<Long> category_ids);
}
