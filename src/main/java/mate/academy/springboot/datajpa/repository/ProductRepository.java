package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    @Modifying
    @Query(value = "update Product p set p.title = ?2, p.price = ?3 where p.id = ?1")
    void update(Long id, String title, BigDecimal price);

    @Query(value = "from Product p where p.price between :from and :to")
    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    @Query(value = "from Product p where p.category.id in (?1)")
    List<Product> findAllByCategoryIdIn(List<Long> categoryId);
}
