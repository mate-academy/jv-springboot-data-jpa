package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    List<Product> findAllBetweenTwoPrices(BigDecimal from, BigDecimal to);

    List<Product> getAllInCategories(List<String> categoryNames);

    Product update(Product product);

    void delete(Long id);
}
