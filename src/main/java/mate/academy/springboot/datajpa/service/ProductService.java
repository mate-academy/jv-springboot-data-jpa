package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    void update(Product product);

    void delete(Long id);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findAll(Map<String, String> params);
}
