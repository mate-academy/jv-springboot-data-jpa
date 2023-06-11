package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService extends GenericService<Product> {
    List<Product> findProductByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findAll(List<String> categories);
}
