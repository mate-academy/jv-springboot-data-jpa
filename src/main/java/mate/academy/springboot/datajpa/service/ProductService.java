package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Product save(Product product);

    Product findById(Long id);

    void deletedById(Long id);

    Product updateProduct(Product product);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    public List<Product> findAllByCategoryIn(List<Long> categoryIds);

}
