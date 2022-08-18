package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Product save(Product product);
    Product findById(Long id);
    void delete(Long id);
    void update(Long id, Product product);
    List<Product> getProductsBetween(BigDecimal from, BigDecimal to);
    List<Product> findAll(Map<String, String> params);
}
