package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("from Product where price between ?1 and ?2")
    List<Product> findByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findAllByCategoryIn(Set<String> categoryNames);

}
