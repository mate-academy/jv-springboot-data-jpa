package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    List<Product> getAllByPriceBetween(BigDecimal firstPrice, BigDecimal secondPrice);

    List<Product> findAllByCategory(Map<String, String> params);
}
