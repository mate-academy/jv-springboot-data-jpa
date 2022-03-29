package mate.academy.springboot.datajpa.service;

import java.util.Comparator;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductRatingSort implements Comparator<Product> {
    @Override
    public int compare(Product product, Product t1) {
        return product.getId() > t1.getId() ? 1 : 0;
    }
}
