package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> getAllForPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllForCategoryIn(Map<String, String> params);
}
