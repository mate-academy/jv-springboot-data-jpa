package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllByCategoryIdIn(Collection<Long> categories);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.title = :#{#product.title}, p.price = :#{#product.price}, "
            + "p.category.id = :#{#product.category.id} WHERE p.id = :#{#product.id}")
    int update(Product product);
}
