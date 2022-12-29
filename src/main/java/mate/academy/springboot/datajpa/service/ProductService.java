package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product get(Long productId);

    void deleteById(Long productId);

    void update(Product product);

    List<Product> findAllByPriceBetween(int from, int to);

    List<Product> findAllByCategoryIdIn(List<Long> ids);
}
