package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    void deleteById(Long id);

    Product getById(Long id);

    List<Product> findAllByPriceBetween(BigDecimal fromPrice, BigDecimal toPrice);

    List<Product> findAll();

    List<Product> findAll(Map<String, String> params);
}
