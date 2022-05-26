package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    void deleteById(Long id);

    Product getById(Long id);

    List<Product> getAllProductByPriceIsBetween(BigDecimal startPrice, BigDecimal endPrice);

    List<Product> getAllProductByCategoriesId(List<Long> categoriesId);
}
