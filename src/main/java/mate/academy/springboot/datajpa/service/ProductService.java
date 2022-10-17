package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    Product update(Product product);

    Product getAllByPriceBetween(BigDecimal from, BigDecimal to);
}
