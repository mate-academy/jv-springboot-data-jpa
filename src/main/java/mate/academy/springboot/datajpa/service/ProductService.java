package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product add(Product product);

    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    Product getById(Long id);

    void deleteById(Long id);

    Product update(Long id, Product product);

    List<Product> findAllByCategories(List<Long> categoriesId);
}
