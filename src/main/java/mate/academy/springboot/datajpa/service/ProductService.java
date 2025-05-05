package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    void deleteById(Long id);

    List<Product> findAllByCategory(List<Category> categoriesId);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    Product update(Product toModel);

    void create(Product toModel);

    List<Product> findAll();

    Product findById(Long id);
}
