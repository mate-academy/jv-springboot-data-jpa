package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    public Product save(Product product);

    public Product getById(Long id);

    public Product update(Product product);

    public void deleteById(Long id);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);
}
