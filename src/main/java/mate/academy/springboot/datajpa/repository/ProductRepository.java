package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "from Product p where p.price between ?1 and ?2")
    List<Product> findAllByPriceBetween(BigDecimal from,BigDecimal to);

    @Query(value = "from Product p where p.category in :category")
    List<Product> findAllByCategory(@Param("category") List<Category> category);

}
