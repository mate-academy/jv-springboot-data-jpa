package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getId(Long id);

    void delete(Long id);

    Product update(Product model);

    List<Product> findAll();

    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllByCategoryIn(List<Category> categories);
}
