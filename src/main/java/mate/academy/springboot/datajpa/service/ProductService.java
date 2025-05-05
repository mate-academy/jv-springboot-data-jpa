package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.entity.Product;

public interface ProductService {
    Product save(Product product);

    void delete(Long id);

    Product findById(Long id);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findAllByCategoryNames(List<String> categoryNames);
}
