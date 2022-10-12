package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    void update(Product product);

    void delete(Long id);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findAllByCategoryIdIn(List<Long> categories);
}
