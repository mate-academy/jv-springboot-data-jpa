package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product add(Product product);

    Product update(Product product);

    Product getById(Long id);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    void delete(Long id);

    List<Product> findAllByCategories(String categoryName);
}
