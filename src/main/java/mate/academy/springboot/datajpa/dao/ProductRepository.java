package mate.academy.springboot.datajpa.dao;

import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getAllByPriceBetween(Double min, Double max);

    @Query("FROM Product p JOIN FETCH p.category WHERE p.category.id IN ?1")
    List<Product> getAllByCategory(List<Long> categoryIds);
}
