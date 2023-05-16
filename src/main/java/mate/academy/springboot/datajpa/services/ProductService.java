package mate.academy.springboot.datajpa.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.models.Product;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findAllByCategories(List<String> categories);

    List<Product> findAll(Map<String, String> params);
}
