package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    Product update(Long id, Product product);

    Product getById(Long id);

    void deleteById(Long id);

    List<Product> getProductsByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getProductsByCategories(List<Long> categoriesId);
}
