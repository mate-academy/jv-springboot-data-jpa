package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product get(Long id);

    Product add(Product product);

    void delete(Long id);

    Product update(Long id, Product product);

    List<Product> getByPriceBetween(Long minPrice, Long maxPrice);

    List<Product> getByCategoryIn(List<String> categoryNames);
}
