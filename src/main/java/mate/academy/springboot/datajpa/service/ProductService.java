package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product saveOrUpdate(Product product);

    Product get(Long id);

    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getAllByCategoryNames(String[] categories);

    void delete(Long id);
}
