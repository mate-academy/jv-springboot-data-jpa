package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    List<Product> findByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findByCategory(List<Category> categories);

    Product update(Product product);

    void deleteById(Long id);
}
