package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.data.jpa.domain.Specification;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    Product update(Long id, Product product);

    List<Product> getAllByCategories(Specification<Product> spec);

    List<Product> findAllByPriceBetween(BigDecimal fromPrice, BigDecimal toPrice);
}
