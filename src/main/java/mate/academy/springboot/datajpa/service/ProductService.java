package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product add(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    void update(Product product);

    List<Product> getAllBetweenPrice(BigDecimal from, BigDecimal to);

    List<Product> getAllByCategories(List<Long> ids);
}
