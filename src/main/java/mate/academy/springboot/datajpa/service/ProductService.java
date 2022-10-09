package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService extends CrudService<Product> {
    List<Product> getProductsByPriceBetween(BigDecimal fromPrice, BigDecimal toPrice);

    List<Product> getProductsByCategories(Map<String, String> params);
}
