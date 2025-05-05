package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    List<Product> findAllPriceBetweenValues(BigDecimal firstPrice, BigDecimal twoPrice);

    List<Product> findAllByCategories(Map<String, String> params);
}
