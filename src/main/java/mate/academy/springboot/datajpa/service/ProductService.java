package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Product save(Product Product);

    Product get(Long id);

    void remove(Long id);

    Product update(Product Product);

    List<Product> findAll(BigDecimal from, BigDecimal to);

    List<Product> findAll(Map<String, String> filters);
}
