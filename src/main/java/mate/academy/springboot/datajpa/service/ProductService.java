package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product create(Product product);

    Product getById(Long id);

    void delete(Long id);

    Product update(Product product, Long id);

    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllByCategoryNames(List<String> categoryNames);
}
