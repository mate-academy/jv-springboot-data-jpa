package mate.academy.springboot.datajpa.repository;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.id = ?1 and p.deleted = false")
    Product getById(Long id);

    @Query("update Product p set p.deleted = true where p.id = ?1 and p.deleted = false")
    void deleteById(Long id);

    @Query("select p from Product p where p.category in (?1) and p.deleted = false")
    List<Product> getByCategories(List<Category> categories);

    @Query("select p from Product p where p.price between ?1 and ?2 and p.deleted = false")
    List<Product> getByPriceBetween(Integer minPrice, Integer maxPrice);
}
