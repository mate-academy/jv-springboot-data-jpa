package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    void deleteById(Long id);

    Product update(Product product);

    List<Product> findProductsByCategoryIn(Set<Category> categories);
}
