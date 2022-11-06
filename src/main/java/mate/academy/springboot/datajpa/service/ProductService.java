package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    Product update(Long id, Product product);

    List<Product> getByPriceBetween(BigDecimal fromPrice, BigDecimal toPrice);

    List<Product> getAllByCategory(Map<String, String> parameters);
}
