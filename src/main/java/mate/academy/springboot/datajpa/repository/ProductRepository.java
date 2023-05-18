package mate.academy.springboot.datajpa.repository;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query("update Product p set p.title = ?2, p.price = ?3, p.category = ?4 where p.id = ?1")
    void update(Long id, String title, Long price, Category category);

    @Query("from Product p where p.category.id = ?1")
    List<Product> getProductsByCategory(Long categoryId);

    @Query("from Product p where p.price between ?1 and ?2")
    List<Product> getProductsByPriceBetween(Long from, Long to);
}
