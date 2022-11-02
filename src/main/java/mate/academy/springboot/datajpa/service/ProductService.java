package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    Product update(Product product);

    void delete(Long id);

    List<Product> findAllByPriceBetween(BigDecimal from,BigDecimal to);

    List<Product> findAllByCategoryIn(List<Category> categories);
}
