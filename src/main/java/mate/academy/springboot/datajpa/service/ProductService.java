package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.models.Category;
import mate.academy.springboot.datajpa.models.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getByCategories(List<Category> categories);
}
