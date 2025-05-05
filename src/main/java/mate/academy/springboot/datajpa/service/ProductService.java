package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product update(Long id, Product product);

    void delete(Long id);

    List<Product> findAll(Map<String, String> params);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    Product findById(Long id);

}
