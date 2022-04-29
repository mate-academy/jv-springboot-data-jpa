package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getProductById(Long id);

    void delete(Long id);

    List<Product> getAll();

    List<Product> findAll(Map<String, String> params);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);
}
