package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product create(Product product);

    Product get(Long id);

    Product update(Product product);

    void delete(Long id);

    List<Product> getProductsByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getProductsByCategories(List<Long> categoryIds);
}
