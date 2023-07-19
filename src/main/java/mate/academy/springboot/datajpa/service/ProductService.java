package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService extends BaseService<Product> {
    List<Product> getAllByPriceBetween(BigDecimal from, BigDecimal to);

    List<Product> getByCategoryIds(Collection<String> categories);
}
