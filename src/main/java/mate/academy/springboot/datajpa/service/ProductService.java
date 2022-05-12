package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    void deleteById(Long id);

    List<Product> getAllByPriceIsBetween(BigDecimal start, BigDecimal end);

    List<Product> getAllByCategoryIdIsIn(List<Long> categories);
}