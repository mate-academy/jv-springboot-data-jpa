package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Product save(Product product);

    List<Product> findAll();

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    Product getById(Long id);

    void deleteById(Long id);

    Product update(Product product);

    Product findInCategories(List<Product> categoriesIds);
}
