package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.models.Product;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getByCategoryId(Long categoryId);
}
