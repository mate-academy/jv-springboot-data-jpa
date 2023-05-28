package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    boolean deleteById(Long id);

    List<Product> getByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getByCategoryIds(List<Long> categoryIds);
}
