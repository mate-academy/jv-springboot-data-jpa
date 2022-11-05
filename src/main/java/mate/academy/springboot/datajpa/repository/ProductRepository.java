package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    @Modifying
    @Query(value = "update Product p set p.title = ?2, p.price = ?3 where p.id = ?1")
    void update(Long id, String title, BigDecimal price);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    @Query (value = "from Product p where p.category.id = ?1")
    List<Product> findAllByCategoryIdIn(List<Long> categoriesId);
}
