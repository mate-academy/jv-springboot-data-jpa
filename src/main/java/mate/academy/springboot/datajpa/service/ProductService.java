package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product save(Product category);

    Product findById(Long id);

    void delete(Long id);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findAllByCategoryNameIn(List<String> categories);
}
