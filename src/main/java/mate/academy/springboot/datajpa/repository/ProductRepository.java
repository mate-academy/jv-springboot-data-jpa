package mate.academy.springboot.datajpa.repository;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("from Product p where p.price between ?1 and ?2")
    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    @Query("from Product p where p.category = ?1")
    List<Product> findAllByCategory(Category category);
}
