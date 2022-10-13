package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    void delete(Long id);

    Product update(Long id,Product product);

    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllProductsByCategory(List<Long> categories);
}
