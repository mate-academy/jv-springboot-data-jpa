package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void delete(Long id);

    void update(Product product);

    List<Product> getProductsPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getProductsCategoriesIdIn(List<Long> categoriesId);
}
