package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    Product getById(Long id);

    Product deleteById(Long id);

    Product update(Long id, Product product);

    List<Product> getAllByPriceBetween(BigDecimal priceStartsWith, BigDecimal priceUpTo);

    List<Product> getAllProductsWithCategories(Map<String, String> params);
}
