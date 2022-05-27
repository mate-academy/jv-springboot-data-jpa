package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService extends CrudService<Product, Long> {
    List<Product> getProductByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getProductsByCategoryIn(List<Category> categoryList);
}
