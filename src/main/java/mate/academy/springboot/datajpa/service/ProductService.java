package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);
}
