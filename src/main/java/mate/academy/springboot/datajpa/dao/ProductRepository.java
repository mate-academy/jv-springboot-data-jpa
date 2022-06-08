package mate.academy.springboot.datajpa.dao;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query(value = "UPDATE product SET title = :title, price = :price, "
            + "category_id = :categoryId WHERE id = :id", nativeQuery = true)
    void update(@Param("id") Long id,
                @Param("title") String title,
                @Param("price") BigDecimal price,
                @Param("categoryId") Long categoryId);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);
}
