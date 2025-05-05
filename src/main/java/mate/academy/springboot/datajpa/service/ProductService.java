package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.models.Product;

public interface ProductService extends AbstractService<Product> {
    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getByCategoryId(Long categoryId);
}
