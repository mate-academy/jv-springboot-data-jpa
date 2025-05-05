package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    Product update(Product product);

    void delete(Long id);

    List<Product> getByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllByCategories(Map<String, String> params);
}
