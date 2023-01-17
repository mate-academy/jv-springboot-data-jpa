package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product createOrUpdate(Product product);

    Product getById(Long id);

    void delete(Long id);

    List<Product> findAllBetweenPrice(BigDecimal priceFrom, BigDecimal priceTo);

    List<Product> findAll(Map<String, String> params);
}
