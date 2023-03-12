package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product saveOrUpdate(Product product);

    Product get(Long id);

    void delete(Long id);

    List<Product> getAllBetweenTwoPrices(BigDecimal priceFrom, BigDecimal priceTo);

    List<Product> getAllByCategory(List<Category> categories);
}
