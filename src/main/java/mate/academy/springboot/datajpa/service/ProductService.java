package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    Product get(Long id);

    void deleteById(Long id);

    Product update(Product product);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findAllByParams(Map<String, String> params);
}
