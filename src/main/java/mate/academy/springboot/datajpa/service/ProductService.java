package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    boolean delete(Long id);

    Product update(Product product);

    List<Product> findAllByPriceBetween(BigDecimal priceOne, BigDecimal priceTwo);

    List<Product> findAll(Map<String, String> params);
}
