package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void delete(Product product);

    List<Product> findAll(BigDecimal from, BigDecimal to);

    List<Product> findAll(Map<String, String> params);
}
