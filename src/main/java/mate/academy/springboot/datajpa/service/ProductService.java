package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    List<Product> getAll();

    List<Product> getAllByPricePriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllByCategoryNameIn(Set<String> categories);
}
