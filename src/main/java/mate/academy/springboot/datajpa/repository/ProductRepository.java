package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p"
            + " join fetch p.category"
            + " where p.price between ?1 and ?2")
    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    @Query("select p from Product p"
            + " join fetch p.category"
            + " where p.category.name in ?1")
    List<Product> findAllByCategory_NameIn(List<String> categories);
}
