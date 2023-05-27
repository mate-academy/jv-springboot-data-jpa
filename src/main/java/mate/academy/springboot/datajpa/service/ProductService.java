package mate.academy.springboot.datajpa.service;

import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product saveProduct(Product product);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(Product product);

    List<Product> getAllProductsWherePriceBetween(Double from, Double to);

    List<Product> getAllProductsWhereCategoriesIn(List<Category> categories);
}
