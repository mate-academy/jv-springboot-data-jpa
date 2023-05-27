package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    void deleteById(Long id);

    Product update(Long id, Product product);

    List<Product> getAllBetweenPrice(BigDecimal from, BigDecimal to);

    List<Product> getAllByCategories(List<String> categoriesIn);
}
