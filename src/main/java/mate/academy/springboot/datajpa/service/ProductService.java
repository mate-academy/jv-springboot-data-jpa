package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Optional<Product> find(Long id);

    void delete(Long id);

    List<Product> getAllByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo);

    List<Product> getAllByCategoryIds(List<Long> ids);
}
