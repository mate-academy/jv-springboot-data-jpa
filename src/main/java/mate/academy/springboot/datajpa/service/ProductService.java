package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    Product update(Product product);

    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllByCategories(List<String> categories);

    void deleteById(Long id);
}
