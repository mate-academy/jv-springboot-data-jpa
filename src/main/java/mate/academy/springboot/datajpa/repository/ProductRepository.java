package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {
    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    @Transactional
    @Modifying
    @Query("update Product p set p.title = ?1, p.category = ?2, p.price = ?3 where p.id = ?4")
    void update(String title, Category category, BigDecimal price, Long id);
}
