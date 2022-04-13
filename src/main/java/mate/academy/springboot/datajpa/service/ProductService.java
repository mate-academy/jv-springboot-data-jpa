package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Optional<Product> getById(Long id);

    void delete(Product product);

    List<Product> findProductByPriceIsBetween(BigDecimal from, BigDecimal to);

    List<Product> findAllByParams(Map<String, String> params);
}
