package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    void deleteProductById(Long id);

    List<Product> findProductsByCategory(List<Category> categoriesId);

    List<Product> findProductsByPrice(BigDecimal from, BigDecimal to);

    Product updateProduct(Product toModel);

    void addProduct(Product toModel);

    List<Product> findAllProducts();

    Product findProductById(Long id);
}
