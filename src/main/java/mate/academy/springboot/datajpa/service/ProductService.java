package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    List<Product> getAll();

    Optional<Product> getById(Long id);

    void deleteById(Long id);

    void update(Product product);

    List<Product> getAllBetweenPrice(BigDecimal from, BigDecimal to);

    List<Product> getByCategory(Category category);
}
