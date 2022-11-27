package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getByID(Long id);

    void deleteBy(Long id);

    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllByCategoryIdIn(List<Long> ids);
}
