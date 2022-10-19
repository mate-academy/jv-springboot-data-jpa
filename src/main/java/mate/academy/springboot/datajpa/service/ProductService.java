package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    void delete(Long id);

    List<Product> getAllBuThePriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllByTheCategoriesIn(Map<String, String> params);
}
