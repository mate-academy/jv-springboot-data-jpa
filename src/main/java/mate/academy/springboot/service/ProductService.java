package mate.academy.springboot.service;

import mate.academy.springboot.model.Category;
import mate.academy.springboot.model.Product;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService extends GenericService<Product> {
    List<Product> getByPrice(BigDecimal from, BigDecimal to);

    List<Product> getByCategories(List<Category> categories);
}
