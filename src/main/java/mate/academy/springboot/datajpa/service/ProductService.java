package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    Product update(Long id, Product product);

    List<Product> findAllBetweenPrice(BigDecimal from, BigDecimal to);

    List<Product> findInCategories(List<String> categories);

    void delete(Long id);
}
