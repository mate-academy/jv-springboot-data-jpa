package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    Product get(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> findAllByCategories(Map<String, String> params);
}
