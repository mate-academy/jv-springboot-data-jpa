package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Product;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Product save(Product product);
    Product get(Long id);
    Product update(Product product);
    void delete(Long id);
    Product findAllByPriceBetween(BigDecimal from, BigDecimal to);
    List<Product> findAllByCategory(Map<String, String> param);
}
