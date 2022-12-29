package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.domain.Product;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    void deleteById(Long id);

    List<Product> findAllBetweenPrice(BigDecimal from, BigDecimal to);

    List<Product> findAllByCategory(Map<String, String> params);
}
