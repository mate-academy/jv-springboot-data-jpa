package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    Product getById(Long id);

    void delete(Long id);

    List<Product> findByPrice(BigDecimal from, BigDecimal to);

    List<Product> findByCategory(List<String> categories);
}
