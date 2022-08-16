package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {
    List<Product> findAllByPriceBetweenOrderByPriceAsc(BigDecimal min, BigDecimal max);

    List<Product> findAll(Specification specification);

    @Query("from Product p where p.price between ?1 and ?2 order by 1 desc")
    List<Product> findAllByPriceBetweenDesc(BigDecimal min, BigDecimal max);
}
