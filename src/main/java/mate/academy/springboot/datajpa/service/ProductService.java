package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    void deleteById(Long id);

    List<Product> findAllProductsByParams(Map<String, String> params);

    List<Product> getProductsWithPriceBetween(BigDecimal from, BigDecimal to);
}
