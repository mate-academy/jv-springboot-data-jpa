package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    void remove(Long id);

    Product update(Product product);

    List<Product> getAll(BigDecimal from, BigDecimal to);

    List<Product> getAll(List<Long> categories);
}
