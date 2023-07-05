package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {

    Product add(Product category);

    Product findById(Long productId);

    void deleteById(Long productId);

    Product update(Product product);

    List<Product> findByPriceBetween(Float priceFrom, Float priceTo);

    List<Product> findByCategories(List<String> categories);
}
