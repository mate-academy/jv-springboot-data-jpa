package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    Product get(Long id);

    void delete(Product product);

    Product update(Product product);

    List<Product> findAllByCategory(List<Category> category);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);
}
