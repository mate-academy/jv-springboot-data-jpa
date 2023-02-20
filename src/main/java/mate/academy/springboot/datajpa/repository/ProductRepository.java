package mate.academy.springboot.datajpa.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {
    List<Product> findAllByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo);

    @Modifying
    @Query(value = "update producrs p set p.title = :title, p.price = :price, "
            + "p.category_id = :categoryId where p.id = :id", nativeQuery = true)
    void updateProduct(@Param(value = "id") Long id, @Param(value = "title") String title,
                       @Param(value = "price") BigDecimal price,
                       @Param(value = "categoryId") Long categoryId);
}
