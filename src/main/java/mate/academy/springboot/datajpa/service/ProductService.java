package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void delete(Product product);

    Product update(Product product);

    List<Product> findAll(BigDecimal from, BigDecimal to);

    List<Product> findAll(Map<String, String> params);
}
