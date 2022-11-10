package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void delete(Long id);

    List<Product> getProductsWithPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getProductsByCategoriesIn(List<Category> categories);
}
