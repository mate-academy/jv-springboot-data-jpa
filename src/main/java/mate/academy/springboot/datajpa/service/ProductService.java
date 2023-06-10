package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductService {
    Product create(Product product);

    List<Product> findAll();

    Product findById(Long id);

    void delete(Long id);

    Product update(Product product);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findAllByCategoryIn(@RequestParam List<Long> categoryIds);

}
