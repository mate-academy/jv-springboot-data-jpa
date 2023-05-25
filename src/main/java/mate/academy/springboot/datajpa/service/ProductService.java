package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product add(Product product);

    Product getById(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> findProductsByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findProductsByCategoryNameIn(List<String> categoryNames);
}
