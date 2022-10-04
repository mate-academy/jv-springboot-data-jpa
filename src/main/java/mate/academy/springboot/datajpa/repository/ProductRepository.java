package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends
        JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @Query("from Product p join fetch p.category where p.id = ?1")
    Product getById(Long id);

    @Query("from Product p join fetch p.category where p.price between ?1 and ?2")
    List<Product> getAllWherePriceBetween(BigDecimal from, BigDecimal to);

    @Query("from Product p join fetch p.category c where c.name in ?1")
    List<Product> getAllProductsByCategories(List<String> categories);
}
