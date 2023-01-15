package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    void deleteById(Long id);

    List<Product> findAll();

    Product getById(Long id);

    List<Product> findAllByPriceBetween(BigDecimal fromPrice, BigDecimal toPrice);

    List<Product> findAllByCategoryIn(Collection<Category> categories);
}
