package service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findAll(Map<String, String> params);
}
