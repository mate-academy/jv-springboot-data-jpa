package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);
    
    Product getById(Long id);
    
    void deleteById(Long id);
    
    List<Product> findAllByPriceBetween(BigDecimal fromPrice, BigDecimal toPrice);
    
    List<Product> getByCategories(List<Long> categoryIds);
}
