package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService extends AbstractService<Product, Long> {
    List<Product> getAllWherePriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllProductsInCategories(List<Category> categories);
}
