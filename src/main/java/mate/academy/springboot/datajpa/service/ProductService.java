package mate.academy.springboot.datajpa.service;

import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Product create(Product product);

    Product getById(Long id);

    void deleteById(Long id);

    List<Product> getAllByPriceBetween(Integer from, Integer to);

    List<Product> findAll(Map<String, String> params);
}
