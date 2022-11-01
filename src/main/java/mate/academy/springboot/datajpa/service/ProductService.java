package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Product;
import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product getById(Long id);
    Product update(Product product);
    void delete(Long id);
    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);
    List<Product> findAllByCategory(List<String> categories);
}
