package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public Product save(Product product);

    public Product get(Long id);

    public List<Product> getAll();

    public void delete(Long id);

    public Product update(Product product);

    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    public List<Product> findAllByCategoryName(List<String> names);
}
