package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product create(Product product);
    Optional<Product> getById(Long id);
    void deleteById(Long id);
    Product update(Product product);
    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);
    List<Product> getAllByCategoriesIn(List<Category> categories);
}
