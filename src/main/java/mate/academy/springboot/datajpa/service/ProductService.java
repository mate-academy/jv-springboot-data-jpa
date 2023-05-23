package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product add(Product product);
    Product get(Long id);
    Product update(Product product);
    void delete(Long id);
    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);
    List<Product> findByCategories(List<String> categories);
}
