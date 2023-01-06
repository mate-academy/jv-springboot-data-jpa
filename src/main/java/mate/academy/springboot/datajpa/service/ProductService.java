package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product getById(Long id);
    void deleteById(Long id);
    Product update(Product product);
    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);
    List<Product> getAllByCategories(List<Category> categories);
}
