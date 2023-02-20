package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product add(Product product);

    void update(Product product);

    Product get(Long id);

    void remove(Long id);

    List<Product> findAll(Map<String, String> params);

    List<Product> findAllByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo);
}
