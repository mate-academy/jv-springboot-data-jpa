package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product add(Product category);

    Product findById(Long productId);

    void deleteById(Long productId);

    Product update(Product product);

    List<Product> findByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo);

    List<Product> findByCategories(List<String> categories);
}
