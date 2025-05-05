package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {

    Product save(Product product);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(Product product);

    List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> findAllByCategoryIn(List<Category> categories);
}
