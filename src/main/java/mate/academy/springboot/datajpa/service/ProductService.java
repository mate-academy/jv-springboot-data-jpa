package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    Product getById(Long id);

    Product update(Product product);

    void deleteById(Long id);

    List<Product> getProductsByPriceBetween(BigDecimal min, BigDecimal max);

    List<Product> getProductsByCategories(List<Category> categories);
}
