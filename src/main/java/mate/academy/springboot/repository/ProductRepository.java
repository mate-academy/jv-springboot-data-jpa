package mate.academy.springboot.repository;

import java.util.List;
import mate.academy.springboot.model.Category;
import mate.academy.springboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategory_Name(String categoryName);

    List<Product> findProductsByPriceBetween(Double from, Double to);

    List<Product> findProductsByCategory(Category category);
}
