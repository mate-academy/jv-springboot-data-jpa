package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    Product update(Product product, Long categoryId);

    boolean remove(Long id);

    List<Product> findAll(Map<String, String> params);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);
}
