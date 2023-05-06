package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    Product update(Product product);

    void delete(Long id);

    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getByCategory(List<String> categories);
}
