package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.Optional;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    Optional<Product> findById(Long id);

    @Override
    void deleteById(Long id);

    @Transactional
    @Modifying
    @Query("update Product p set p.title=?1, p.price=?2, p.category=?3 where p.id=?4")
    void update(String title, BigDecimal price, Category category, Long id);
}
