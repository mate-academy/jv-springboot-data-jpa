package mate.academy.springboot.datajpa.repository;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.category in (?1)")
    List<Product> getByCategories(List<Category> categories);

    List<Product> getByPriceBetween(Integer lowerPrice, Integer higherPrice);
}
